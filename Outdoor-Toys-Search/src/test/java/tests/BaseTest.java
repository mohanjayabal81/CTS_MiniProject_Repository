package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

import utils.DriverManager;
import java.io.IOException;

public class BaseTest {

    // Shared WebDriver instance for all test classes
    protected static WebDriver driver;

    // Manages browser setup and teardown
    protected static DriverManager dm;

    // Executes once before the entire test suite
    
    @BeforeSuite
    public void setupSuite() throws IOException {

        // Initialize driver only if not already created
        if (driver == null) {
            dm = new DriverManager();
            driver = dm.getWebDriver();

            // Ensure browser window is maximized before tests run
            driver.manage().window().maximize();
        }
    }

    // Executes once after all test cases are completed
    
    @AfterSuite
    public void tearDownSuite() throws IOException {

        // Cleanly shutdown browser and associated resources
        if (dm != null) {
            dm.killDriver();
        }
    }
}