 package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    
    // Holds key-value pairs from the properties file
    Properties properties;
    
    // Used to read the properties file from disk
    FileInputStream file;

    // Constructor loads the properties file when object is created
    public PropertiesReader() throws IOException {
        properties = new Properties();

        // Get current project directory
        String nm = System.getProperty("user.dir");
        System.out.println("This is the property" + nm);

        // Build absolute path of object repository file
        String pathname = nm + "\\objectRepository\\object.properties";

        // Open and load the properties file
        file = new FileInputStream(pathname);
        properties.load(file);
    }

    // Returns value for the given key from properties file
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    // Closes FileInputStream to release system resources
    public void killFileInputStream() throws IOException {
        file.close();
    }
}
