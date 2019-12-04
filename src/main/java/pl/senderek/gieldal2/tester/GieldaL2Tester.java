package pl.senderek.gieldal2.tester;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.senderek.gieldal2.tester.controller.Client;
import pl.senderek.gieldal2.tester.controller.ClientDecisionTree;
import pl.senderek.gieldal2.tester.controller.ClientRandom;
import pl.senderek.gieldal2.tester.model.GeneratorLog;
import pl.senderek.gieldal2.tester.model.TestContext;
import pl.senderek.gieldal2.tester.model.TestParams;
import pl.senderek.gieldal2.tester.model.User;
import pl.senderek.gieldal2.tester.service.external.*;
import pl.senderek.gieldal2.tester.service.internal.GeneratorLogService;

import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootApplication
public class GieldaL2Tester implements CommandLineRunner {
    @Value("${test.TEST_TYPE}")
    private String TEST_TYPE;
    @Value("${test.CLIENTS_QUANTITY}")
    private Integer CLIENTS_QUANTITY;
    @Value("#{'${test.CLIENTS_DATA}'.split(' ')}")
    private List<String> CLIENTS_DATA;

    @Value("${test.MAX_NO_OF_ACTIONS}")
    private Integer MAX_NO_OF_ACTIONS;
    @Value("${test.OFFER_SLEEP_TIME}")
    private Integer OFFER_SLEEP_TIME;
    @Value("${test.OFFER_MAX_NUM_OF_PRICE_CHANGES}")
    private Integer OFFER_MAX_NUM_OF_PRICE_CHANGES;
    @Value("#{new Double('${test.OFFER_FIRST_PRICE_DIFF}')}")
    private Double OFFER_FIRST_PRICE_DIFF;
    @Value("#{new Double('${test.BUY_OFFER_PRICE_DIFF}')}")
    private Double BUY_OFFER_PRICE_DIFF;
    @Value("#{new Double('${test.SELL_OFFER_PRICE_DIFF}')}")
    private Double SELL_OFFER_PRICE_DIFF;

    private final GeneratorLogService generatorLogService;
    private final UserService userService;
    private final ShareService shareService;
    private final StockService stockService;
    private final BuyOfferService buyOfferService;
    private final SellOfferService sellOfferService;

    public GieldaL2Tester(GeneratorLogService generatorLogService, UserService userService, ShareService shareService, StockService stockService,
                          BuyOfferService buyOfferService, SellOfferService sellOfferService) {
        this.generatorLogService = generatorLogService;
        this.userService = userService;
        this.shareService = shareService;
        this.stockService = stockService;
        this.buyOfferService = buyOfferService;
        this.sellOfferService = sellOfferService;
    }

    public static void main(String[] args) {
        SpringApplication.run(GieldaL2Tester.class, args);
    }

    @Override
    public void run(String... args) {
        //testDB();
        //testAPI();
        //testClient();

        Date testStartTime = new Date();
        TestParams testParams = new TestParams(MAX_NO_OF_ACTIONS, OFFER_SLEEP_TIME, OFFER_MAX_NUM_OF_PRICE_CHANGES, OFFER_FIRST_PRICE_DIFF,
                BUY_OFFER_PRICE_DIFF, SELL_OFFER_PRICE_DIFF);
        List<Client> clients = new ArrayList<>();
        for (int i = 1; i <= CLIENTS_QUANTITY; i++) {
            if (CLIENTS_DATA.size() >= 2 * i) {
                User user = new User();
                user.setLogin(CLIENTS_DATA.get(2 * i - 2));
                user.setPassword(CLIENTS_DATA.get(2 * i - 1));

                if (TEST_TYPE.equals("TREE"))
                    clients.add(new ClientDecisionTree(userService, shareService, stockService, buyOfferService, sellOfferService, user, (long) i, testStartTime, testParams));
                else if (TEST_TYPE.equals("RANDOM"))
                    clients.add(new ClientRandom(userService, shareService, stockService, buyOfferService, sellOfferService, user, (long) i, testStartTime, testParams));
            }
        }

        for (Client client : clients)
            client.start();
    }

    private void testDB() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        System.out.println(dateFormat.format(new Date()) + "  Running test...");
        GeneratorLog log = new GeneratorLog();
        log.setActiveClientsQuantity(10);
        log.setBackendTime(11);
        log.setClientId(12L);
        log.setDbSelectsQuantity(13);
        log.setDbSelectsTime(14);
        log.setDbUpdatesQuantity(15);
        log.setDbUpdatesTime(16);
        log.setReqNo(17);
        log.setReqTime(18);
        log.setReqType("TYPE");
        log.setRespType("RTYPE");
        log.setTestType("TTYPE");
        log.setTestStartTime(new Date());
        long insertStart = System.currentTimeMillis();
        generatorLogService.save(log);
        long insertTime = System.currentTimeMillis() - insertStart;

        long selectStart = System.currentTimeMillis();
        List<GeneratorLog> logs = generatorLogService.findAll();
        long selectTime = System.currentTimeMillis() - selectStart;

        System.out.println(dateFormat.format(new Date()) + "  Test complete! Inserted 1 log in " + insertTime + "ms, retrieved " + logs.size() + " logs in " + selectTime + "ms.");
        for (int i = 1; i <= 10; i++) {
            selectStart = System.currentTimeMillis();
            List<GeneratorLog> logs2 = generatorLogService.findAll();
            selectTime = System.currentTimeMillis() - selectStart;
            System.out.println(dateFormat.format(new Date()) + "  Select test #" + i + " complete! Retrieved " + logs2.size() + " logs in " + selectTime + "ms.");
        }
    }

    private void testAPI() {
        TestContext context = new TestContext(11L);
        User authUser = new User();
        authUser.setLogin("admin");
        authUser.setPassword("admin");
        String token = userService.authenticateUser(authUser);
        User user = userService.getUserContext(token);
        if (user.getName().equals("admin"))
            user.setName("ADMIN");
        else
            user.setName("admin");
        user.setPassword("admin");
        userService.modifyUser(context, user, token);
        Optional<User> changedUser = userService.getUser(context, 1L, token);

        User newUser = new User();
        newUser.setName("a");
        newUser.setSurname("b");
        newUser.setLogin("c");
        newUser.setPassword("d");
        newUser.seteMail("a@b.c");
        newUser.setValue(123.4);
        userService.createUser(context, newUser, token);
        token = userService.authenticateUser(newUser);
        newUser = userService.getUserContext(token);
        userService.deleteUser(context, newUser.getId(), token);

        System.out.println("TEST DONE");
    }

    private void testClient() {
        User user = new User();
        user.setLogin("admin");
        user.setPassword("admin");

        Date date = Calendar.getInstance().getTime();
        TestParams testParams = new TestParams(MAX_NO_OF_ACTIONS, OFFER_SLEEP_TIME, OFFER_MAX_NUM_OF_PRICE_CHANGES, OFFER_FIRST_PRICE_DIFF,
                BUY_OFFER_PRICE_DIFF, SELL_OFFER_PRICE_DIFF);

        Client client = new ClientDecisionTree(userService, shareService, stockService, buyOfferService, sellOfferService, user, 1L, date, testParams);
        client.start();
    }
}