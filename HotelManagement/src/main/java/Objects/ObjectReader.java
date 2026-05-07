package Objects;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class ObjectReader {

   
    private Properties properties;

    
    public ObjectReader() {
        
        properties = new Properties();
        try {
            // Create a FileInputStream pointing to the Config.properties file using a dynamic project path
            FileInputStream fileInput = new FileInputStream(System.getProperty("user.dir") + "/ObjectRepository/Config.properties");
            // Load the properties from the file into the Properties object
            properties.load(fileInput);
            // Close the file input stream to release system resources
            fileInput.close();
        } catch (IOException error) {
            // Print the error details if file loading or reading fails
            System.out.println("Failed to load Config.properties: " + error.getMessage());
         
        }
    }

    // Reads and returns the value of "BaseUrl" from the properties file
    public String getUrl() {
        return properties.getProperty("BaseUrl");
    }
}