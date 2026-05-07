package Amazon.TestCase;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import BrowserImplementation.BrowserLaunch;
import PAGES.HomePage;
import PAGES.SearchPage;
import UTILS.ObjectReader;
import UTILS.ReportGenarator;
import UTILS.ScreenShotClicker;                                     

public class AmazonSearchTest {

    BrowserLaunch b;        // Handles browser launching
    HomePage h;             // Provides methods to interact with the homepage
    SearchPage sp;          // Provides methods to interact with the search results page
    WebDriver driver;       // Controls the browser throughout the test
    WebDriverWait w;        // Waits for elements/conditions before proceeding
    ObjectReader or;        // Reads locators and config values from the properties file
    ScreenShotClicker sc;   // Captures screenshots on test pass or fail
    ReportGenarator rg;
    ExtentReports test;

    // Test 1: Verifies the Amazon homepage title matches the expected value
    @Test(priority = 0)
    public void HomePageVerify() throws InterruptedException, IOException {
    	ExtentTest home = test.createTest("This is HomePage Report"); 
        try {
            driver.get(h.getHomeUrl());                                             // Navigate the browser to the Amazon homepage URL
            String expectedResult = h.verify_Title();                               // Get the expected page title from the properties file
            w.until(ExpectedConditions.titleIs(expectedResult));                    // Wait until the browser title matches the expected title
            String actualResult = driver.getTitle();                                // Retrieve the actual browser title after the page loads
            Assert.assertEquals(actualResult, expectedResult, "Title did not match!"); // Assert actual title equals expected title
            String path =sc.takeScreenshot("HomePageVerify_Pass");                  // Take a screenshot on test pass
            home.addScreenCaptureFromPath(path); 
            home.pass("HomePage_reterived");

        } catch (AssertionError e) {
            String path = sc.takeScreenshot("HomePageVerify_Fail");                               // Take a screenshot on assertion failure
            home.addScreenCaptureFromPath(path);
            home.fail("HomePageFail too retrived");
            throw e;                                                                // Re-throw the error so TestNG marks the test as FAILED
        }
    }

    
    
 // Test 2: Verifies the search box is visible and performs a product search
    // Depends on HomePageVerify passing first
    @Test(dependsOnMethods = {"HomePageVerify"})
    public void SearchBox() throws IOException {
        ExtentTest searchBox_test = test.createTest("This is SearchBox Report");
        try {
            WebElement searchBox = h.searchBoxLocate();                             // Locate the search input box on the homepage
            
            w.until(ExpectedConditions.visibilityOf(searchBox));                    // Wait until the search box is visible on the page
            
            h.searchProduct(searchBox);                                             // Type the search keyword and submit the search
            
            String path = sc.takeScreenshot("SearchBox_Pass");                     // Take a screenshot on test pass
            
            searchBox_test.addScreenCaptureFromPath(path);
            
            searchBox_test.pass("SearchBox_retrieved");

        } catch (AssertionError e) {
            String path = sc.takeScreenshot("SearchBox_Fail");                     // Take a screenshot on assertion failure
            searchBox_test.addScreenCaptureFromPath(path);
            searchBox_test.fail("SearchBox_Fail retrieved");
            throw e;                                                                // Re-throw the error so TestNG marks the test as FAILED
        } catch (IOException ex) {
            ex.printStackTrace();                                                   // Print stack trace if a file I/O error occurs
        }
    }
    
    
    

    // Test 3: Verifies the "Results" heading is displayed on the search results page
    // Depends on SearchBox passing first
    @Test(dependsOnMethods = {"SearchBox"})
    public void SearchPage() throws IOException {
        ExtentTest searchPage_test = test.createTest("This is SearchPage Report");
        try {
            WebElement actualElement = sp.getActualSearchPageText();                // Locate the "Results" heading element on the search results page
            w.until(ExpectedConditions.visibilityOf(actualElement));                // Wait until the "Results" heading is visible
            String expectedResult1 = sp.getExpectedSearchPageText();               // Get the expected heading text from the properties file
                           
            String actualResult1 = actualElement.getText();                         // Extract the actual heading text from the page element
            
            Assert.assertEquals(expectedResult1, actualResult1);                   // Assert actual heading matches the expected heading
            String path = sc.takeScreenshot("SearchPage_Pass");                    // Take a screenshot on test pass
            searchPage_test.addScreenCaptureFromPath(path);
            searchPage_test.pass("SearchPage_retrieved");

        } catch (AssertionError e) {
            String path = sc.takeScreenshot("SearchPage_Fail");                    // Take a screenshot on assertion failure
            searchPage_test.addScreenCaptureFromPath(path);
            searchPage_test.fail("SearchPage_Fail retrieved");
            throw e;                                                                // Re-throw the error so TestNG marks the test as FAILED
        } catch (IOException ex) {
            ex.printStackTrace();                                                   // Print stack trace if a file I/O error occurs
        }
    }

    
    
    
    
    // Test 4: Validates the result count text matches the expected regex pattern
    // Depends on SearchPage passing first
    @Test(dependsOnMethods = {"SearchPage"})
    public void SearchPageValidate() throws IOException {
        ExtentTest searchPageValidate_test = test.createTest("This is SearchPageValidate Report");
        try {
            String value = sp.getSearchBoxValueO();                                 // Get the current search keyword retained in the search box
            
            String actual = sp.getActualSearchPageValidateText();                   // Get the actual result count text from the page
            System.out.println("Actual Text      : " + actual);                    // Log the actual result count text to the console
            String expected = sp.getExpectedSearchPageValidateText(value);          // Build the expected regex pattern using the search keyword
           
            boolean assertValue = actual.matches(expected);                         // Check if the actual text matches the expected regex pattern
            Assert.assertTrue(assertValue);                                         // Assert the regex match is true
            String path = sc.takeScreenshot("SearchPageValidate_Pass");            // Take a screenshot on test pass
            searchPageValidate_test.addScreenCaptureFromPath(path);
            searchPageValidate_test.pass("SearchPageValidate_retrieved");

        } catch (AssertionError e) {
            String path = sc.takeScreenshot("SearchPageValidate_Fail");            // Take a screenshot on assertion failure
            searchPageValidate_test.addScreenCaptureFromPath(path);
            searchPageValidate_test.fail("SearchPageValidate_Fail retrieved");
            throw e;                                                                // Re-throw the error so TestNG marks the test as FAILED
        } catch (IOException ex) {
            ex.printStackTrace();                                                   // Print stack trace if a file I/O error occurs
        }
    }
    
    
    
    

    // Test 5: Sorts search results by "Newest Arrivals" and verifies the sort selection
    // Depends on SearchPageValidate passing first
    @Test(dependsOnMethods = {"SearchPageValidate"})
    public void SearchPageSort() throws IOException {
        ExtentTest searchPageSort_test = test.createTest("This is SearchPageSort Report");
        try {
            WebElement sort = sp.getSortlist();                                     // Locate the "Sort by:" dropdown button
            sort.click();                                                           // Click to open the sort dropdown menu
            WebElement sortNew = sp.getSortlistNewestArrival();                     // Locate the "Newest Arrivals" option in the dropdown
            sortNew.click();                                                        // Click to select "Newest Arrivals" as the sort option
            String actual = sp.getActualSortlistResult();                           // Get the currently displayed sort option text from the dropdown
            String expected = sp.getExpectedSortListResult();                       // Get the expected sort option text from the properties file
            
            Assert.assertEquals(actual, expected);                                  // Assert the actual sort option matches the expected value
            String path = sc.takeScreenshot("SearchPageSort_Pass");                // Take a screenshot on test pass
            searchPageSort_test.addScreenCaptureFromPath(path);
            searchPageSort_test.pass("SearchPageSort_retrieved");

        } catch (AssertionError e) {
            String path = sc.takeScreenshot("SearchPageSort_Fail");                // Take a screenshot on assertion failure
            searchPageSort_test.addScreenCaptureFromPath(path);
            searchPageSort_test.fail("SearchPageSort_Fail retrieved");
            throw e;                                                                // Re-throw the error so TestNG marks the test as FAILED
        } catch (IOException ex) {
            ex.printStackTrace();                                                   // Print stack trace if a file I/O error occurs
        }
    }
    
    
    

    // Test 6: Collects the top 5 non-sponsored products and verifies exactly 5 were found
    // Depends on SearchPageSort passing first
    @Test(dependsOnMethods = {"SearchPageSort"})
    public void GetTop5Phones() throws IOException {
        ExtentTest getTop5Phones_test = test.createTest("This is GetTop5Phones Report");
        try {
            Map<String, String> top5 = sp.getTop5Products();                       // Collect the top 5 organic product names and prices from the results page
            int i = 1;                                                              // Counter to number the products in the console output
            for (Map.Entry<String, String> entry : top5.entrySet()) {              // Iterate over each product entry in the map
                System.out.println(i++ + ". " + entry.getKey() + " - " + entry.getValue()); // Print the product number, name, and price
            }
            Assert.assertEquals(top5.size(), 5, "Expected 5 but got: " + top5.size()); // Assert exactly 5 products were collected
            String path = sc.takeScreenshot("_Top5Phones_Pass");                   // Take a screenshot on test pass
            getTop5Phones_test.addScreenCaptureFromPath(path);
            getTop5Phones_test.pass("GetTop5Phones_retrieved");

        } catch (AssertionError e) {
            String path = sc.takeScreenshot("_Top5Phones_Fail");                   // Take a screenshot on assertion failure
            getTop5Phones_test.addScreenCaptureFromPath(path);
            getTop5Phones_test.fail("GetTop5Phones_Fail retrieved");
            throw e;                                                                // Re-throw the error so TestNG marks the test as FAILED
        }
    }
    
    
    
    
    // Runs once before all test methods: sets up browser, page objects, and utilities
    @BeforeClass
    public void beforeClass() throws IOException, InterruptedException {
        b = new BrowserLaunch();                                                    // Create a BrowserLaunch instance to manage browser setup
        driver = b.chromeLaunch();                                                  // Launch the Chrome browser and get the WebDriver instance
        driver.manage().window().maximize();                                        // Maximize the browser window for consistent test execution
        sp = new SearchPage(driver);                                                // Initialize the SearchPage object with the active driver
        h = new HomePage(driver);                                                   // Initialize the HomePage object with the active driver
        sc = new ScreenShotClicker(driver);                                         // Initialize the ScreenShotClicker utility with the active driver
        w = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));            // Set up WebDriverWait with a 10-second timeout for element conditions
        rg = new ReportGenarator();
        test = rg.reportGenerator();
    }

    // Runs once after all test methods: closes the browser and ends the session
    @AfterClass
    public void afterClass() {
        driver.quit();  // Close all browser windows and terminate the WebDriver session
        test.flush();
    }
}