package Tests;

import java.io.IOException;
import java.time.LocalDate;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Browser_Implementation.Browsers;
import Objects.ObjectReader;
import Pages.LandingPage;
import Utils.ModularMethods;

// Main test class for automating Trivago hotel search functionality
public class TrivagoTest {

    private Browsers browser;
    private ObjectReader util;
    private LandingPage page;
    private WebDriver driver;
    //private ModularMethods reuse;
    private ExtentReports extent;
    private ExtentSparkReporter spark;
    private ExtentTest test;
    private String path;

    // Sets up ExtentReports, launches Chrome browser, loads config, and navigates to the base URL
    @BeforeClass
    public void setUp() throws IOException {
        spark = new ExtentSparkReporter("reports/TrivagoTestReport.html");
        spark.config().setDocumentTitle("Trivago Test Report");
        spark.config().setReportName("Hotel Search Automation");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        browser = new Browsers();
        driver = browser.getBrowser();
        util = new ObjectReader();
       // reuse = new ModularMethods();
        page = new LandingPage(driver);
        driver.get(util.getUrl());
        driver.manage().window().maximize();
        System.out.println("window");
    }

    // Enters a destination from DataProvider, captures screenshot, and verifies URL is not empty
    @Test(priority = 1, dataProvider = "place")
    public void testDestination(String place) throws IOException {
        test = extent.createTest("Test Destination - " + place);
        page.destination(place);
        test.log(Status.INFO, "Entered destination: " + place);
        //path = reuse.screenshot(driver, "Destination");
        //test.addScreenCaptureFromPath(path);
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl != null && !currentUrl.isEmpty(),
            "URL is empty after entering destination — navigation failed");
        test.pass("Destination entered successfully");
    }

    // Selects check-in (tomorrow) and check-out (day after) dates using the date picker
    @Test(priority = 2)
    public void testDateSelection() throws IOException {
        test = extent.createTest("Test Date Selection");
        LocalDate checkIn = LocalDate.now().plusDays(7);
        LocalDate checkOut = LocalDate.now().plusDays(8);
        page.datePickerClick();
        test.log(Status.INFO, "Opened date picker");
        page.selectDate(checkIn);
        test.log(Status.INFO, "Check-in date selected: " + checkIn);
        page.selectDate(checkOut);
        test.log(Status.INFO, "Check-out date selected: " + checkOut);
        //path = reuse.screenshot(driver, "Date_Selection");
       // test.addScreenCaptureFromPath(path);
    }

    // Decreases guest count using minus button and applies the selection
    @Test(priority = 3)
    public void testGuestSelection() throws IOException {
        test = extent.createTest("Test Guest Selection");
        page.minusButton();
        test.log(Status.INFO, "Decreased guest count");
        page.applyButton();
        test.log(Status.INFO, "Applied guest selection");
        test.pass("Guest count updated successfully");
        //path = reuse.screenshot(driver, "Number_Of_Guests");
        //test.addScreenCaptureFromPath(path);
    }

    // Sorts hotel results by rating using the sort dropdown and applies the filter
    @Test(priority = 4)
    public void testSortByRating() throws IOException {
        test = extent.createTest("Test Sort By Rating");
        page.sortButton();
        test.log(Status.INFO, "Clicked sort button");
        page.sortByRatingButton();
        test.log(Status.INFO, "Selected sort by rating");
        page.filterButton();
        test.log(Status.INFO, "Applied filter");
        test.pass("Sorted by rating successfully");
    }

    // Verifies top 5 hotel ratings are in descending order using SoftAssert and validates ratings are positive
    @Test(priority = 5)
    public void testRatingsInDescendingOrder() {
        test = extent.createTest("Test Ratings Descending Order");
        SoftAssert softAssert = new SoftAssert();
        double temp = Double.MAX_VALUE;
        for (int i = 1; i <= 5; i++) {
            double rating = Double.parseDouble(page.getRating(i));
            test.log(Status.INFO, "Hotel " + i + " rating: " + rating);
            softAssert.assertTrue(rating <= temp,
                "Rating at position " + i + " (" + rating + ") is greater than previous (" + temp + ")");
            if (rating > temp) {
                test.log(Status.FAIL, "Out of order at position " + i + ": " + rating + " > " + temp);
            }
            temp = rating;
        }
        for (int i = 1; i <= 5; i++) {
            double rating = Double.parseDouble(page.getRating(i));
            Assert.assertTrue(rating > 0,
                "Rating at position " + i + " is invalid: " + rating);
        }
        softAssert.assertAll();
        test.pass("All ratings are in descending order");
    }

    // Supplies destination values ("Mumbai") to testDestination method
    @DataProvider(name = "place")
    public Object[] getPlace() {
        return new Object[] { "Mumbai" };
    }

    // Closes the browser and flushes the ExtentReport to generate the HTML report file
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        extent.flush();
    }
}