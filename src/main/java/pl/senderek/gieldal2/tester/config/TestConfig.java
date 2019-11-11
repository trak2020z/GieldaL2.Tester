package pl.senderek.gieldal2.tester.config;

import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public class TestConfig {
    @Value("tester.api-address")
    public static String API_URL;

    @Value("tester.test-type")
    public static String TEST_TYPE;

    @Value("tester.clients-quantity")
    public static Integer CLIENTS_QUANTITY;

    @Value("tester.start-time")
    public static Date TEST_START_TIME;
}
