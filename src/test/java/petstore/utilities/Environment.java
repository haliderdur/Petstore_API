package petstore.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Environment {

    public static Properties properties;
    public static String environment;

    static {
        environment = System.getProperty("environment") != null ? System.getProperty("environment") : ConfigurationReader.getProperty("environment");

        try {
            String path = System.getProperty("user.dir") + "/src/test/resources/environments/" + environment + ".properties";

            FileInputStream input = new FileInputStream(path);
            properties = new Properties();
            properties.load(input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getVariable(String varName) {
        return properties.getProperty(varName);
    }


}
