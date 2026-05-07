package testCases;

import java.io.IOException;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import browserImplementation.BrowserConfiguration;
import configReader.ObjectReader;
import pages.FlipkartHomePage;
import pages.FlipkartMobileFetching;
import pages.FlipkartResultsPage;
import utility.ReusableMethods;

// End-to-end test for Flipkart mobile search and price validation
public class TestFlipkart {

    private WebDriver driver;
    private ObjectReader or;
    private FlipkartHomePage homePage;
    private FlipkartResultsPage resultsPage;
    private FlipkartMobileFetching mobileFetching;

    // Launch browser, open Flipkart and setup report
    @BeforeTest
    public void setUp() throws IOException {
        or = new ObjectReader();

        // Setup extent report
        ReusableMethods.setupReport();

        // Ask user to choose browser at runtime (1/2/3)
        BrowserConfiguration browser = new BrowserConfiguration();
        driver = browser.launchBrowser();

        // Open Flipkart and maximize
        driver.get(or.getBaseUrl());
        driver.manage().window().maximize();

        ReusableMethods.test.info("Browser launched and Flipkart opened.");
    }

    // Test 1 — Close popup, search mobiles under 15000, verify results
    @Test(priority = 1)
    public void testSearchAndVerify() throws IOException, InterruptedException {
        try {
            homePage = new FlipkartHomePage(driver);
            resultsPage = new FlipkartResultsPage(driver);

            // Close login popup
            Thread.sleep(2000);
            homePage.closeLoginPopup();
            ReusableMethods.test.info("Login popup handled.");

            // Search for mobiles under 15000
            Thread.sleep(2000);
            homePage.searchMobilesUnder15000();
            ReusableMethods.test.info("Search performed for mobiles under 15000.");

            // Verify results page loaded with correct label
            resultsPage.verifySearchResultCriteria();

            // Pass with screenshot
            String path = ReusableMethods.captureScreenshot(driver, "searchResult");
            ReusableMethods.test.pass("Search verified - mobiles under 15000").addScreenCaptureFromPath(path);

        } catch (Exception e) {
            // Fail with screenshot
            String path = ReusableMethods.captureScreenshot(driver, "searchFail");
            ReusableMethods.test.fail("testSearchAndVerify failed: " + e.getMessage()).addScreenCaptureFromPath(path);
        }
    }

    // Test 2 — Apply price filter, select Pie OS, sort by Newest First
    @Test(priority = 2, dependsOnMethods = "testSearchAndVerify")
    public void testFiltersAndSort() throws IOException, InterruptedException {
        try {
            resultsPage = new FlipkartResultsPage(driver);

            // Open filter panel
            resultsPage.openFiltersPanel();
            ReusableMethods.test.info("Filters panel opened.");

            // Set max price to Rs.10000
            resultsPage.setMaxPriceTo10000();
            ReusableMethods.test.info("Max price set to Rs.10000.");

            // Select Pie OS version
            resultsPage.selectPieOsVersionFilter();
            ReusableMethods.test.info("OS Version Pie selected.");

            // Sort by Newest First
            resultsPage.sortByNewestFirst();

            // Pass with screenshot
            String path = ReusableMethods.captureScreenshot(driver, "filtersApplied");
            ReusableMethods.test.pass("Filters applied and sorted by Newest First").addScreenCaptureFromPath(path);

        } catch (Exception e) {
            // Fail with screenshot
            String path = ReusableMethods.captureScreenshot(driver, "filterFail");
            ReusableMethods.test.fail("testFiltersAndSort failed: " + e.getMessage()).addScreenCaptureFromPath(path);
        }
    }

    // Test 3 — Fetch top 5 mobiles and validate first mobile price < Rs.30000
    @Test(priority = 3, dependsOnMethods = "testFiltersAndSort")
    public void testMobileValidation() throws IOException {
        try {
            mobileFetching = new FlipkartMobileFetching(driver);

            // Fetch top 5 mobiles — price assertion runs inside for mobile 1
            Map<String, String> top5 = mobileFetching.fetchTop5Mobiles();

            // Log all 5 mobile names and prices in report
            int rank = 1;
            for (Map.Entry<String, String> entry : top5.entrySet()) {
                ReusableMethods.test.info(rank + ". " + entry.getKey() + " | " + entry.getValue());
                rank++;
            }
            
            //printing in the console
            rank = 1;
            for (Map.Entry<String, String> entry : top5.entrySet()) {
                System.out.println((rank + ". " + entry.getKey() + " | " + entry.getValue()));
                rank++;
            }

            // Pass with screenshot
            String path = ReusableMethods.captureScreenshot(driver, "mobileResults");
            ReusableMethods.test.pass("Top 5 mobiles fetched and price validated").addScreenCaptureFromPath(path);

        } catch (Exception e) {
            // Fail with screenshot
            String path = ReusableMethods.captureScreenshot(driver, "mobileFail");
            ReusableMethods.test.fail("testMobileValidation failed: " + e.getMessage()).addScreenCaptureFromPath(path);
        }
    }

    // Close browser and save the extent report
    @AfterTest
    public void tearDown() {
        driver.quit();
        ReusableMethods.flushReport();
        System.out.println("INFO Browser closed and report saved.");
    }
}