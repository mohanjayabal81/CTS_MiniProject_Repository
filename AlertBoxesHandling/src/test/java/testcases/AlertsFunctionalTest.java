package testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


import browserimplementation.BrowserLoader;
import configreader.ObjectReader;
import pages.AlertsPage;
import utils.AlertHandlers;


//Functional tests for browser alert interactions
public class AlertsFunctionalTest {

	 private WebDriver driver;       
	 private BrowserLoader loader;
	 private ObjectReader config;
	 private AlertsPage page;
	 private AlertHandlers alertHandlers;
	 
	 private ExtentReports extent;
	 private ExtentTest test;

	    // Initializes browser, config, and page object before test class runs
	 @Parameters("browser")
	 @BeforeClass
	 public void setup(String browser) {
	    try {
	        loader = new BrowserLoader();
	        config = new ObjectReader();

	        if (browser.equalsIgnoreCase("edge")) {
	            driver = loader.launchEdge();
	        } else if (browser.equalsIgnoreCase("chrome")) {
	            driver = loader.launchChrome();
	        }

	        // Extent Report setup
	        ExtentSparkReporter spark = new ExtentSparkReporter("Reports/ExtentReport.html");
	        extent = new ExtentReports();
	        extent.attachReporter(spark);

	        driver.get(config.getBaseUrl());
	        page = new AlertsPage(driver);
	        alertHandlers = new AlertHandlers(driver);

	        Reporter.log("Browser: " + browser + " | URL: " + config.getBaseUrl(), true);
	    } catch (Exception e) {
	        Reporter.log("Setup failed: " + e.getMessage(), true);
	        throw new RuntimeException("Aborting tests — browser setup failed.", e);
	    }
	}



 // Navigates to the Alerts section via SwitchTo menu
    @Test(priority = 0)
    public void navigateToAlertsPage() {
        test = extent.createTest("Navigate To Alerts Page");
        try {
            page.navigateToAlertsSection();
            Reporter.log("Navigated to Alerts page via SwitchTo menu.", true);
            test.pass("Navigated to Alerts page via SwitchTo menu.");
        } catch (Throwable e) {
            Reporter.log("FAIL: " + e.getMessage(), true);
            test.fail("Navigation failed: " + e.getMessage());
            alertHandlers.captureScreenshot(test, "Navigate To Alerts Page");
            Assert.fail("Navigation failed: " + e.getMessage());
        }
    }
    


    // Verifies simple alert text and accepts it
    @Test(priority = 1)
    public void verifyAlertTextAndAccept() {
        test = extent.createTest("Verify Simple Alert");
        try {
            page.clickAlertButton();
            //Gets text from the  alert dialog box
            String alertText = alertHandlers.getAlertText();
            Reporter.log("Expected: 'I am an alert box!' | Actual: '" + alertText + "'", true);
            Assert.assertTrue(alertText.contains("I am an alert box!"),
                    "Alert text mismatch! Actual: " + alertText);
            //Accepts the  alert dialog box
            alertHandlers.acceptAlert();
            Reporter.log("PASS: Simple alert verified and accepted.", true);
            test.pass("Simple alert verified and accepted. Alert text: '" + alertText + "'");
        } catch (Throwable e) {
            Reporter.log("FAIL: " + e.getMessage(), true);
            test.fail("Alert did not appear or could not be handled: " + e.getMessage());
            alertHandlers.captureScreenshot(test, "Verify Simple Alert");
            Assert.fail("Alert did not appear or could not be handled: " + e.getMessage());
        }
    }
    

    
    
 // Verifies confirm box shows correct message on cancel
    @Test(priority = 2)
    public void verifyConfirmCancel() {
        test = extent.createTest("Verify Confirm Cancel");
        try {
            page.openConfirmBox();
            //Dismisses (cancels) the confirm alert dialog
            alertHandlers.dismissAlert();
            String actualMsg = page.getConfirmResultMessage();

            Reporter.log("Expected: 'You Pressed Cancel' | Actual: '" + actualMsg + "'", true);
            Assert.assertEquals(actualMsg, "You Pressed Cancel",
                    "Confirm cancel message mismatch! Actual: " + actualMsg);
            Reporter.log("PASS: Confirm cancel message verified.", true);
            test.pass("Confirm cancel message verified. Actual: '" + actualMsg + "'");
        } catch (Throwable e) {
            Reporter.log("FAIL: " + e.getMessage(), true);
            alertHandlers.captureScreenshot(test, "Verify Confirm Cancel");
            test.fail("Confirm box could not be handled: " + e.getMessage());
            Assert.fail("Confirm box could not be handled: " + e.getMessage());
        }
    }
    
    
    // Verifies prompt box accepts input and displays correct result message
    @Test(priority = 3)
    public void verifyPromptInput() {
        test = extent.createTest("Verify Prompt Input");
        SoftAssert softAssert = new SoftAssert();
        String name = config.getPromptInputName();

        try {
            page.openPromptBox();
            //Types given text into prompt alert and accepts it
            alertHandlers.typeAndAccept(name);
            String actualMsg = page.getPromptResultMessage();
            Reporter.log("Input: '" + name + "' | Expected: 'Hello " + name + " How are you today' | Actual: '" + actualMsg + "'", true);
            softAssert.assertEquals(actualMsg, "Hello " + name + " How are you today",
                    "Prompt message mismatch! Actual: " + actualMsg);
            softAssert.assertTrue(actualMsg.contains(name),
                    "Name not found in prompt result message.");
            test.pass("Prompt input verified. Input: '" + name + "' | Result: '" + actualMsg + "'");
        } catch (Throwable e) {
            Reporter.log("FAIL: " + e.getMessage(), true);
            test.fail("Prompt box could not be handled: " + e.getMessage());
            alertHandlers.captureScreenshot(test, "Verify Prompt Input");
            softAssert.fail("Prompt box could not be handled: " + e.getMessage());
        }
        softAssert.assertAll();
    }
    

    // Quits the browser after all tests in the class complete
    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
        if (extent != null) extent.flush();
    }
}