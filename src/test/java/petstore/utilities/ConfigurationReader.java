package petstore.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

    //1- Create the object of Properties
    private static Properties properties = new Properties();

    static {
        try {
            // Open file in java memory: FileInputStream
            FileInputStream file = new FileInputStream("configuration.properties");

            // Load the properties object using FileInputStream object
            properties.load(file);

            // Close the file
            file.close();

        } catch (IOException e) {
            System.out.println("File not found in the ConfigurationReader class.");
            e.printStackTrace();
        }

    }

    public static String getProperty(String keyword) {
        return properties.getProperty(keyword);
    }


}
