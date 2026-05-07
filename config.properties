package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import utilities.ConfigReader;
import utilities.DriverFactory;


// BaseTest.java
// Sets up and tears down WebDriver for each test method.

public class BaseTest {

    protected WebDriver driver;

    @BeforeTest
    public void setup() throws Exception {
        driver = DriverFactory.getDriver(ConfigReader.get("browser"));
        driver.get(ConfigReader.get("url"));
    }

    @AfterTest
    public void tearDown() {
        // Null check prevents crash if setup failed
        if (driver != null) {
            driver.quit();
        }
    }
}