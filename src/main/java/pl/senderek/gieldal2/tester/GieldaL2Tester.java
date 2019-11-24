package pl.senderek.gieldal2.tester;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.senderek.gieldal2.tester.model.GeneratorLog;
import pl.senderek.gieldal2.tester.model.TestContext;
import pl.senderek.gieldal2.tester.model.User;
import pl.senderek.gieldal2.tester.service.external.UserService;
import pl.senderek.gieldal2.tester.service.internal.GeneratorLogService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class GieldaL2Tester implements CommandLineRunner {

    private final GeneratorLogService generatorLogService;
    private final UserService userService;

    public GieldaL2Tester(GeneratorLogService generatorLogService, UserService userService) {
        this.generatorLogService = generatorLogService;
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(GieldaL2Tester.class, args);
    }

    @Override
    public void run(String... args) {
//        testDB();
        testAPI();
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
        userService.authenticateUser(authUser);
        List<User> userList = userService.getAllUsers(context);
        Optional<User> optionalUser = userService.getUser(context, 1L);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            if(user.getName().equals("admin"))
                user.setName("ADMIN");
            else
                user.setName("admin");
            user.setPassword("admin");
            userService.modifyUser(context, user);
        }
        Optional<User> changedUser = userService.getUser(context, 1L);
        System.out.println("TEST DONE");
    }
}