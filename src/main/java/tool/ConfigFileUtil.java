package tool;

import model.TestParams;
import org.json.JSONObject;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author Lukasz Wojtas
 */
public class ConfigFileUtil {

    private final String HIBERNATE_CONFIG = "hibernate";
    private final String TEST_CONFIG = "test";

    private String fileName;

    public ConfigFileUtil(String fileName) {
        if (fileName != null && !fileName.isEmpty())
            this.fileName = fileName;
        else
            this.fileName = "GieldaL2Tester.conf";
    }

    public void readConfigFile() throws Exception {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);

        String str = scanner.nextLine();
        JSONObject obj = new JSONObject(str);

        if (obj.has(HIBERNATE_CONFIG))
            HibernateUtil.initSessionFactory(obj.getJSONObject(HIBERNATE_CONFIG));
        else
            throw new Exception("Missing hibernate configuration");
        if (obj.has(TEST_CONFIG))
            TestParams.initTestParams(obj.getJSONObject(TEST_CONFIG));
        else
            throw new Exception("Missing test configuration");
    }

    public void generateConfigFile() throws Exception {
        JSONObject obj = new JSONObject();
        obj.put(HIBERNATE_CONFIG, HibernateUtil.getConfigTemplate());
        obj.put(TEST_CONFIG, TestParams.getConfigTemplate());

        PrintWriter printWriter = new PrintWriter(fileName);
        printWriter.println(obj.toString());
        printWriter.close();
    }

}
