package pl.senderek.gieldal2.tester.tool;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Klasa sprawdzająca obecność i zawartość pliku konfiguracyjnego application.properties
 */
public class ApplicationPropertiesChecker {
    /**
     * Lista kluczy application.properties
     */
    private List<String> keys;

    /**
     * Konstruktor wypełniający listę kluczy
     */
    public ApplicationPropertiesChecker() {
        keys = new ArrayList<>();
        keys.add("spring.datasource.url");
        keys.add("spring.datasource.username");
        keys.add("spring.datasource.password");
        keys.add("spring.datasource.driver-class-name");

        keys.add("spring.jpa.show-sql");
        keys.add("spring.jpa.hibernate.ddl-auto");
        keys.add("spring.jpa.properties.hibernate.naming-strategy");
        keys.add("spring.jpa.properties.hibernate.dialect");
        keys.add("spring.jpa.properties.hibernate.connection.pool_size");

        keys.add("test.API_URL");
        keys.add("test.TEST_TYPE");
        keys.add("test.CLIENTS_QUANTITY");
        keys.add("test.CLIENTS_DATA");
        keys.add("test.MAX_NO_OF_ACTIONS");
        keys.add("test.OFFER_SLEEP_TIME");
        keys.add("test.OFFER_MAX_NUM_OF_PRICE_CHANGES");
        keys.add("test.OFFER_FIRST_PRICE_DIFF");
        keys.add("test.BUY_OFFER_PRICE_DIFF");
        keys.add("test.SELL_OFFER_PRICE_DIFF");
        keys.add("test.RANDOM_CHANCES");
    }

    /**
     * Metoda sprawdzająca obecność application.properties
     *
     * @return true - jeżeli file istnieje i posiada wszystkie klucze
     */
    public boolean checkApplicationProperties() {
        boolean ret = false;

        try {
            File file = new File("application.properties");
            if (file.exists())
                ret = checkKeysCompleteness(file);
            else
                generateDefaultApplicationProperties(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }

    /**
     * Metoda sprawdzająca kompletność kluczy application.properties
     *
     * @param file Plik application.properties
     * @return true - jeżeli file posiada wszystkie klucze, false - jeżeli w file brakuje klucza/kluczy
     * @throws Exception File nie istnieje, program nie posiada uprawnień do odczytu
     */
    private boolean checkKeysCompleteness(File file) throws Exception {
        List<String> keys = new ArrayList<>(this.keys);

        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String key = scanner.next();
            if (key.contains("="))
                key = key.substring(0, key.indexOf("="));

            keys.remove(key);
        }

        if (keys.size() == 0)
            return true;
        else {
            System.err.println("Missing keys in application.properties.");
            return false;
        }
    }

    /**
     * Metoda generująca wzorzec pliku application.properties
     *
     * @param file Plik application.properties
     * @throws Exception Plik nie istnieje, program nie posiada uprawnień do zapisu
     */
    private void generateDefaultApplicationProperties(File file) throws Exception {
        if (file.createNewFile()) {
            PrintWriter printWriter = new PrintWriter(file);

            for (String key : keys)
                printWriter.println(key + "=");

            printWriter.close();
            System.err.println("Missing application.properties has been created. Fill it with proper values.");
        } else
            System.err.println("Missing application.properties cannot be created.");
    }

}