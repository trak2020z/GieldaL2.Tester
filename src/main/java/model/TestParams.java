package model;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Lukasz Wojtas
 */
public class TestParams {

    private static final String API_URL_JSON = "apiURL";
    private static final String TEST_TYPE_JSON = "testType";
    private static final String CLIENTS_QUANTITY_JSON = "clientsQuantity";

    private static String apiURL;
    private static String testType;
    private static Integer clientsQuantity;
    private static Date testStartTime;


    public static void initTestParams(JSONObject obj) throws Exception {
        if (obj.has(API_URL_JSON))
            apiURL = obj.getString(API_URL_JSON);
        else
            throw new Exception("Missing test configuration: " + API_URL_JSON);

        if (obj.has(TEST_TYPE_JSON))
            testType = obj.getString(TEST_TYPE_JSON);
        else
            throw new Exception("Missing test configuration: " + TEST_TYPE_JSON);

        if (obj.has(CLIENTS_QUANTITY_JSON))
            clientsQuantity = obj.getInt(CLIENTS_QUANTITY_JSON);
        else
            clientsQuantity = 1;

        testStartTime = Calendar.getInstance().getTime();
    }

    public static JSONObject getConfigTemplate() throws Exception {
        JSONObject obj = new JSONObject();
        obj.put(API_URL_JSON, "");
        obj.put(TEST_TYPE_JSON, "");
        obj.put(CLIENTS_QUANTITY_JSON, 1);

        return obj;
    }

    public static String getApiURL() {
        return apiURL;
    }

    public static String getTestType() {
        return testType;
    }

    public static Integer getClientsQuantity() {
        return clientsQuantity;
    }

    public static Date getTestStartTime() {
        return testStartTime;
    }
}
