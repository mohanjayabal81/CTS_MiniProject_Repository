package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ObjectReader {
    Properties prop;
    public ObjectReader() throws IOException {
        prop=new Properties();
        FileInputStream fis=new FileInputStream("C:\\Users\\2480347\\IdeaProjects\\AlertsHandling\\src\\main\\resources\\objects.properties");
        prop.load(fis);
    }

    public String getUrl(){
        return prop.getProperty("baseUrl");
    }

    public  String getExpectedTitle(){
        return prop.getProperty("alertsPage.expected.title");
    }

    public  String getSimpleAlertExpMsg(){
        return prop.getProperty("simpleAlert.expected.msg");
    }

    public  String getConfirmAlertExpMsg(){
        return prop.getProperty("confirmAlert.expected.msg");
    }

    public String getName(){
        return prop.getProperty("name");
    }

    public  String getPromptAlertExpMsg(){
        return prop.getProperty("promptAlert.expected.msg");
    }

    public  String getSimpleAlertXpath(){
        return prop.getProperty("alertsPage.handleSimpleAlert.xpath");
    }

    public  String getConfirmationAlertXpath(){
        return prop.getProperty("alertsPage.handleConfirmationAlert.xpath");
    }

    public  String getPromptAlertXpath(){
        return prop.getProperty("alertsPage.handlePromptAlert.xpath");
    }
}
