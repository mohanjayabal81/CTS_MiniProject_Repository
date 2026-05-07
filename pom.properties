package utilities;

import java.io.FileInputStream;
import java.util.Properties;


// ConfigReader.java
// Reads values from config.properties file.

public class ConfigReader {

    private static Properties property = new Properties();

    static {
        String path = System.getProperty("user.dir")
                + "/src/main/resources/config.properties";

        // try-with-resources auto closes FileInputStream
        try (FileInputStream fis = new FileInputStream(path)) {
            property.load(fis);
        } catch (Exception e) {
            System.out.println("Failed to load config.properties: " + e.getMessage());
        }
    }

    public static String get(String key) {
        return property.getProperty(key);
    }
}