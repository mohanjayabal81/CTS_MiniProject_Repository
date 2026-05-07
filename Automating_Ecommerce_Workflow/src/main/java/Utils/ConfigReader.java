package Utils;


import java.io.FileInputStream;

// Used to store key-value pairs from properties file
import java.util.Properties;

public class ConfigReader {

    // Properties object 
    static Properties prop;

    // Method to initialize and load properties file
    public static Properties initProperties() {
        try {
            // Creating FileInputStream 
            FileInputStream fis = new FileInputStream(
                    System.getProperty("user.dir") + "/src/test/resources/config.properties");

            
            prop = new Properties();

            // Loading properties 
            prop.load(fis);

        }
        catch (Exception e) {
           
            e.printStackTrace();
        }

        
        return prop;
    }

   
    public static String getProperty(String key) {
        // Returns value corresponding to the given key
        return prop.getProperty(key);
    }
}