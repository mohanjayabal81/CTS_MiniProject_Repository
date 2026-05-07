package tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import base.BaseTest;
import pages.HomePage;
import pages.ResultsPage;
import utilities.ConfigReader;
import utilities.DateUtil;
import utilities.ScreenshotUtil;


// Test class for IRCTC Train Search flow.
//
// Test 1 - verifyPageLaunch  : Verifies correct page is opened after browser launch.
// Test 2 - verifyTrainSearch : Completes train search and verifies results.

public class TrainSearchTest extends BaseTest {

//  Test 1 - Verify correct page is opened after browser launch.
//  Checks page title and URL contain expected values.

    @Test(description = "Verify IRCTC home page is launched correctly", priority = 0)
    public void verifyPageLaunch() {

        HomePage home = new HomePage(driver);

        String pageTitle  = home.getPageTitle();
        String currentUrl = home.getCurrentUrl();

        System.out.println("Page title  : " + pageTitle);
        System.out.println("Current URL : " + currentUrl);

        // Hard assertion - if wrong page opens, no point running search test
        Assert.assertTrue(pageTitle.contains("IRCTC"),
                "Page title does not contain IRCTC. Actual title: " + pageTitle);
        Assert.assertTrue(currentUrl.contains("irctc.co.in"),
                "URL does not match expected IRCTC site. Actual URL: " + currentUrl);

        System.out.println("Correct page verified successfully");
    }

//  Test 2 - Complete train search from HYD to PUNE and verify results.
//  Extent Reports added to track each step with screenshots at key points.

    @Test(description = "Verify train search from HYD to PUNE with Sleeper class", priority = 1)
    public void verifyTrainSearch() throws Exception {
        // Extent Reports setup
        ExtentReports extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("reports/ExtentReport.html");
        extent.attachReporter(spark);
        ExtentTest test = extent.createTest("IRCTC Train Search - " + ConfigReader.get("fromStation") + " to " + ConfigReader.get("toStation"));

        HomePage home       = new HomePage(driver);
        ResultsPage results = new ResultsPage(driver);

        // Read test data from config
        int daysToAdd       = Integer.parseInt(ConfigReader.get("daysToAdd"));
        String expectedDate = DateUtil.getFutureDate(daysToAdd);

        // Step 1: Handle browser alert if present on page load
        test.info("Handling alert if present on page load");
        home.handleAlert();

        // Screenshot 1 - after page launch and alert handling
        String screenshotAfterLaunch = ScreenshotUtil.captureAndReturn(driver, "after_launch");
        test.addScreenCaptureFromPath(screenshotAfterLaunch);
        test.pass("IRCTC page launched successfully");

        // Step 2: Login with credentials from config
//        test.info("Logging in with credentials from config");
//        home.login(ConfigReader.get("username"), ConfigReader.get("password"));
//        test.pass("Login attempted successfully");

        // Step 3: Enter From and To stations read from config
        test.info("Entering From and To stations");
        home.enterStations(ConfigReader.get("fromStation"), ConfigReader.get("toStation"));

        // Step 4: Select journey date based on days from config
        test.info("Selecting journey date - " + expectedDate);
        home.selectJourneyDate(daysToAdd);

        // Step 5: Select journey class from config
        test.info("Selecting journey class - " + ConfigReader.get("journeyClass"));
        home.selectJourneyClass(ConfigReader.get("journeyClass"));

        // Step 6: Select PWD Concession
        test.info("Selecting PWD Concession");
        home.selectPWDConcession();

        // Screenshot 2 - search form filled, before clicking Search Trains
        String screenshotBeforeSearch = ScreenshotUtil.captureAndReturn(driver, "before_search");
        test.addScreenCaptureFromPath(screenshotBeforeSearch);

        // Step 7: Click Search Trains
        test.info("Clicking Search Trains button");
        home.clickSearchTrains();

     // Step 8: Hard assertion - results page must load before continuing
        // If results do not load, the test stops here with a clear failure message
        try {
            results.waitForResults();
        } catch (Exception e) {
            test.fail("Results page did not load. Error: " + e.getMessage());
            Assert.fail("Results page did not load. Cannot proceed with validation. Error: " + e.getMessage());
        }

        // Step 9: Soft assertions - verify header content and train list
        // All soft assertion failures are collected and reported together at the end
        SoftAssert softAssert = new SoftAssert();

        String headerText = results.getResultHeaderText();
        System.out.println("Result header: " + headerText);
        test.info("Result header: " + headerText);

        // Verify From station in header
        softAssert.assertTrue(headerText.contains(ConfigReader.get("fromName")),
                "From station "+ConfigReader.get("fromName")+" not found in result header");

        // Verify To station in header
        softAssert.assertTrue(headerText.contains(ConfigReader.get("toName")),
                "To station "+ConfigReader.get("toName")+" not found in result header");

        // Verify journey date in header
        softAssert.assertTrue(headerText.contains(expectedDate), "Expected date " + expectedDate + " not found in result header");

        // Verify at least one train is returned in results
        List<String> trainNames = results.getTrainNames();
        softAssert.assertFalse(trainNames.isEmpty(), "No trains found in search results");

        // Step 10: Print train results to console
        System.out.println();
        System.out.println("Total trains found: " + trainNames.size());
        System.out.println();
        int count = 1;
        for (String name : trainNames) {
            System.out.println(count++ + ". " + name);
        }
        System.out.println();

        test.pass("Total trains found: " + trainNames.size());
        int trains = 1;
        for (String name : trainNames) {
            test.info(trains++ + ". " + name);
        }

        // Screenshot 3 - after train results are displayed
        String screenshotAfterResults = ScreenshotUtil.captureAndReturn(driver, "IRCTC_Search_Result");
        test.addScreenCaptureFromPath(screenshotAfterResults);

        // Step 11: Flush extent report to save HTML file
        extent.flush();

        // Trigger all collected soft assertion failures at once
        softAssert.assertAll();
    }
}