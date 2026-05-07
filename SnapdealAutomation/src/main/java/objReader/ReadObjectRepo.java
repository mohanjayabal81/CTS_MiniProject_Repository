package objReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadObjectRepo {

    // Properties object to read data from properties file
    Properties pro = new Properties();

    // Constructor to load the object repository file
    public ReadObjectRepo() throws IOException {

        // Path of the properties file
        File file = new File("C:\\Users\\2478515\\eclipse-workspace\\com.cts.project\\ObjectRepository\\object.properties");

        // Reading the file
        FileInputStream fis = new FileInputStream(file);

        // Load file data into Properties object
        pro.load(fis);
    }

    // Get application URL from properties file
    public String getURL() {
        return pro.getProperty("URL");
    }

}