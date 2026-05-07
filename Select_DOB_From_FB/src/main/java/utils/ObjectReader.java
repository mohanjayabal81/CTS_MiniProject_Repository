package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ObjectReader {
    public static Properties property;
    public ObjectReader() throws IOException {
        property = new Properties();
        FileInputStream fis = new FileInputStream(
            "C:\\Users\\2478761\\eclipse-workspace\\Select_DOB_From_FB\\src\\main\\resources\\obj.properties"
        );
        property.load(fis);
    }
    public String getBrowser() {
        return property.getProperty("browser");
    }

    public String getUrl() {
        return property.getProperty("baseUrl");
    }

    public String getName() {
        return property.getProperty("name");
    }

    public String getSurname() {
        return property.getProperty("surname");
    }

    public String getDay() {
        return property.getProperty("day");
    }

    public String getMonth() {
        return property.getProperty("month");
    }

    public String getYear() {
        return property.getProperty("year");
    }

    public String getMobileNumber() {
        return property.getProperty("mobileNumber");
    }

    public String getGender() {
        return property.getProperty("gender");
    }
}
