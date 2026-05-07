package browserimplementation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

// Handles browser instantiation for test execution 
public class BrowserLoader {

    private WebDriver driver;  


    // Returns the current driver instance 
    public WebDriver getDriver() {
        return driver;
    }

    //Sets an external driver instance (useful for mocking/testing)
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }


    // Launches and returns a Chrome browser instance 
    public WebDriver launchChrome() {
        driver = new ChromeDriver();
        driver.manage().window().maximize(); 
        return driver;
    }

    // Launches and returns an Edge browser instance 
    public WebDriver launchEdge() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();  
        return driver;
    }

    // Launches and returns a Firefox browser instance 
    public WebDriver launchFirefox() {  
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        return driver;
    }
}