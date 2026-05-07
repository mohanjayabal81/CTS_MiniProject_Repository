
package utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/*
 * Utility class for reading object.properties file
 * Keeps locators and test data separate from code
 */
public class ObjectReader {

    private Properties pro;

    public ObjectReader() {

        try {
            String path = System.getProperty("user.dir")
                    + File.separator + "ObjectRepository"
                    + File.separator + "object.properties";

            FileInputStream fis = new FileInputStream(path);

            pro = new Properties();
            pro.load(fis);
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Returns Base URL
    public String geturl() {
        return pro.getProperty("BaseUrl");
    }

    // Returns expected page title
    public String get_ExpectedTitle() {
        return pro.getProperty("ExpectedTitle");
    }

    // Returns expected banner text
    public String get_ExpectedBanner() {
        return pro.getProperty("ExpectedBanner");
    }

    // Generic method to get any locator value
    public String getLocator(String key) {
        return pro.getProperty(key);
    }
}