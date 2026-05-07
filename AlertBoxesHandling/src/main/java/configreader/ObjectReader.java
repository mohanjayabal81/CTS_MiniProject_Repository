package configreader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ObjectReader {
	
	//// holds all key-value pairs from properties file
    private Properties pro;

    //Loads object.properties file into memory on instantiation
    public ObjectReader() {
    	
    	//builds absolute path to properties file using project root
        String path = System.getProperty("user.dir") + "/src/test/resources/object-repository/object.properties";
        
        // auto-closes stream after use
        try(FileInputStream fis = new FileInputStream(path)) {
            pro = new Properties();
            //// parses and loads all key=value pairs into pro
            pro.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Could not load properties file: " + e.getMessage());
        }
    }
    
    //Returns application base URL
    public String getBaseUrl() {
        return pro.getProperty("BaseUrl");
    }
    
 // ── Navigation ───────────────────────────────────────────
    
    //Returns link text for SwitchTo menu
    public String getSwitchToMenu() {
        return pro.getProperty("Alerts.switchToMenu.linkText");
    }

    //Returns XPath for Alerts option under SwitchTo
    public String getAlertsOption() {
        return pro.getProperty("Alerts.alertsOption.xpath");
    }

    // ── Simple Alert ─────────────────────────────────────────
    
    //Returns link text for simple alert tab
    public String getAlertTab() {
        return pro.getProperty("Alerts.alertTab.linkText");
    }
    
    //Returns XPath for simple alert trigger button
    public String getAlertBtn() {
        return pro.getProperty("Alerts.alertBtn.xpath");
    }

    // ── Confirm Alert ─────────────────────────────────────────
    
    //Returns link text for confirm alert tab
    public String getConfirmTab() {
        return pro.getProperty("Alerts.confirmTab.linkText");
    }

    //Returns XPath for confirm alert trigger button
    public String getConfirmBtn() {
        return pro.getProperty("Alerts.confirmBtn.xpath");
    }

    // ── Prompt Alert ──────────────────────────────────────────
    
    //Returns link text for prompt alert tab
    public String getPromptTab() {
        return pro.getProperty("Alerts.promptTab.linkText");
    }

    //Returns XPath for prompt alert trigger button
    public String getPromptBtn() {
        return pro.getProperty("Alerts.promptBtn.xpath");
    }

    // ── Result Messages ───────────────────────────────────────
    //Returns element ID for prompt result message
    public String getResultMsg() {
        return pro.getProperty("Alerts.resultMsg.id");
    }
    
    //Returns element ID for confirm result message
    public String getConfirmMsg() {
        return pro.getProperty("Alerts.confirmMsg.id");
    }
    
    //Returns name value to input in prompt box
    public String getPromptInputName() {
        return pro.getProperty("Alerts.prompt.inputName");
    }
}
