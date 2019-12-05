package pl.senderek.gieldal2.tester;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.senderek.gieldal2.tester.controller.Client;
import pl.senderek.gieldal2.tester.controller.ClientDecisionTree;
import pl.senderek.gieldal2.tester.controller.ClientRandom;
import pl.senderek.gieldal2.tester.model.TestParams;
import pl.senderek.gieldal2.tester.model.User;
import pl.senderek.gieldal2.tester.service.external.*;
import pl.senderek.gieldal2.tester.service.internal.GeneratorLogService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Value("#{'${test.RANDOM_CHANCES}'.split(' ')}")
    private List<Integer> RANDOM_CHANCES;

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
        Date testStartTime = new Date();
        TestParams testParams = new TestParams(MAX_NO_OF_ACTIONS, OFFER_SLEEP_TIME, OFFER_MAX_NUM_OF_PRICE_CHANGES, OFFER_FIRST_PRICE_DIFF,
                BUY_OFFER_PRICE_DIFF, SELL_OFFER_PRICE_DIFF, RANDOM_CHANCES);
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
}