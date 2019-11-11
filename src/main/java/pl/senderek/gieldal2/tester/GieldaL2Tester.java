package pl.senderek.gieldal2.tester;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.senderek.gieldal2.tester.model.GeneratorLog;
import pl.senderek.gieldal2.tester.service.internal.GeneratorLogService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class GieldaL2Tester implements CommandLineRunner {

    private final GeneratorLogService generatorLogService;

    public GieldaL2Tester(GeneratorLogService generatorLogService) {
        this.generatorLogService = generatorLogService;
    }

    public static void main(String[] args) {
        SpringApplication.run(GieldaL2Tester.class, args);
    }

    @Override
    public void run(String... args) {
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
        generatorLogService.save(log);
        List<GeneratorLog> logs = generatorLogService.findAll();
        System.out.println(dateFormat.format(new Date()) + "  Test complete! Inserted 1 log, retrieved " + logs.size() + " logs.");
    }
}