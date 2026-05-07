package tests;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import browser.BrowserManager;
import configReader.ObjectReader;
import pages.HomePage;
import pages.ProductsPage;
import utils.Utilities;

public class TestingPages {
	
	private WebDriver driver;
	private BrowserManager browserManager;
	private ObjectReader objectReader;
	private HomePage homePage;
	private ProductsPage productsPage;
	private Utilities utilities;
	private ExtentReports extent;
	
	
	@BeforeClass
	public void setUp() throws IOException {
	
		browserManager = new BrowserManager();
		
		// Selecting the browser
//		String browserName=browserManager.selectBrowser();
//		
//		// getting browser instance According to the name 
//		if(browserName.equalsIgnoreCase("chrome")) {
//			driver= browserManager.getChrome();
//		}
//		else if(browserName.equalsIgnoreCase("edge")) {
//			driver= browserManager.getEdge();
//		}
//		else{
//			//if user enters any wrong browser name it will stop the execution
//			 Assert.fail("Invalid browser name: " + browserName);
//		}
		
		//code for command propmt
		driver=browserManager.getChrome();
		
		objectReader = new ObjectReader();
		
		homePage= new HomePage(driver);
		
		productsPage = new ProductsPage(driver);
		
		utilities  = new Utilities();
		
		extent = new ExtentReports();
		
		// It generates the HTML report
		ExtentSparkReporter spark = new ExtentSparkReporter("reports/ExtentReport.html");
		
		// Attach the Spark reporter to the ExtentReports object
		extent.attachReporter(spark);
		
	}
	
	@Test(priority=1)
	public void launchApplication() {
		
		// Creates a new test case named "Launching the application" in the report
		ExtentTest test1= extent.createTest("Launching the application");
		
		browserManager.maximizeBrowser(driver);
		
		// Opening the URL
		driver.get(objectReader.getApplicationUrl());
		
		// logs the pass status with message
		test1.pass("Application launched successfully");
	}
	
	@Test(priority=2,dependsOnMethods="launchApplication")
	public void verifyHomePageUrl() throws IOException {
		
		String expectedUrl = objectReader.getApplicationUrl();
		
		String actualUrl=homePage.getCurrentHomePageUrl();
		
		// Creates a new test case named "verifying homePage URL" in the report
		ExtentTest test2= extent.createTest("verifying homepage url");
		
		try {
			Assert.assertEquals(actualUrl,expectedUrl);
			
			// logs the pass status with message
			test2.pass("URL verified successfully");
			
			String path = utilities.captureScreenshot(driver,"HomePage");
			
			// add that screenshot to the extent report
			test2.addScreenCaptureFromPath(path);
		}
		catch(AssertionError e) {
			
			System.out.println("Assertion Error :- "+e.getMessage());
			
			// log the fail status with message
			test2.fail("URL mismatch");
			
			String path=utilities.captureScreenshot(driver,"urlError");
			
			// add that screenshot to the extent report
			test2.addScreenCaptureFromPath(path);
			
			throw e;
		}
		
	}
	
	@Test(priority=3,dependsOnMethods="verifyHomePageUrl")
	public void verifyHomePageTitle() {
		
		String expectedTitle = objectReader.getHomePageTitle();
		
		String actualTitle = homePage.getCurrentHomePageTitle();
		
		// Creates a new test case named "Verifying HomePage Title" in the report
		ExtentTest test3 = extent.createTest("Verifying Homepage Title");
		
		SoftAssert sf1= new SoftAssert();
		
		sf1.assertEquals(actualTitle,expectedTitle);
		try {
			// Fails the test if any soft assertion has failed
			sf1.assertAll();
		
			// logs the pass status with message
			test3.pass("Homepage title verified successfully");
		}
		catch(AssertionError e) {
			
			System.out.println(e.getMessage());
			
			// log the fail status with message
			test3.fail("Homepage title verification failed");
		}
	}
	
	
	@Test(priority=4,dependsOnMethods="verifyHomePageUrl")
	public void testSearchProduct() throws IOException {
		
		// Creates a new test case named "Searching products" in the report
		ExtentTest test4 = extent.createTest("Searching products");
		
		try {
		WebElement searchBox= homePage.getSearchBox();
		
		String path = utilities.captureScreenshot(searchBox,"searchBox");
		
		// add that screenshot to the extent report
		test4.addScreenCaptureFromPath(path);
		
		homePage.searchProduct();
		
		// logs the pass status with message
		test4.pass("Products search successfully done");
		
		}
		catch(TimeoutException e) {
			test4.fail("products search failed");
			throw e;
		}
		
		
	}
	
	
	@Test(priority=5,dependsOnMethods="testSearchProduct")
	public void verifyProductsPageTitle() {
		
		String expectedTitle = objectReader.getProductsPageTitle();
		
		String actualTitle=productsPage.getProductsPageTitle();
		
		// Creates a new test case named "verifying products page title" in the report
		ExtentTest test5= extent.createTest("verifying products page title");
		
		SoftAssert sf2= new SoftAssert();
		
		sf2.assertEquals(actualTitle,expectedTitle);
		try {
			
			// Fails the test if any soft assertion has failed
			sf2.assertAll();
			
			// logs the pass status with message
			test5.pass("Productspage title verified successfully");
			}
		catch(AssertionError e) {
			
			System.out.println(e.getMessage());
			
			//logs the fail status with message
			test5.fail("Productspage title verification failed");
		}
	}
	
	@Test(priority=6,dependsOnMethods="testSearchProduct")
	public void testPriceRangeSelection() {
		
		// Creates a new test case named "setting price range" in the report
		ExtentTest test6= extent.createTest("setting price range");
		
		productsPage.setPriceRange();
		
		// logs the pass status with message
		test6.pass("Price range set successfully");
	}
	
	@Test(priority=7,dependsOnMethods="testPriceRangeSelection")
	public void testSortByPopularity(){
		
		// Creates a new test case named "sorting by popularity" in the report
		ExtentTest test7= extent.createTest("sorting by popularity");
		
		productsPage.sortByPopularity();
		
		// logs the pass status with message
		test7.pass("products successfully sorted by popularity");
	}
	
	@Test(priority=8,dependsOnMethods="testSortByPopularity")
	public void testFetchAndPrintProductNamesAndPrices() throws  IOException {
		
		// Creates a new test case named "printing products names and prices" in the report
		ExtentTest test8= extent.createTest("printing products names and prices");
		
		productsPage.fetchAndPrintProductNamesAndPrices();
		
		// logs the pass status with message
		test8.pass("Products names and prices are printed successfully");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		// scrolls 300 pixels down
		js.executeScript("window.scrollBy(0,200)");
		
		String path = utilities.captureScreenshot(driver,"ProductsPage");
		
		// add that screenshot to the extent report
		test8.addScreenCaptureFromPath(path);
		
		js.executeScript("window.scrollBy(0,300)");
		String path2 = utilities.captureScreenshot(driver,"ProductsPage-2");
		test8.addScreenCaptureFromPath(path2);
		
	}
	
	
	@AfterClass
	public void tearDown()  {
		
		// Writes all test information to the Extent HTML report
		extent.flush();
		
		// closing the driver instance
		browserManager.closeDriver(driver);
	}
	

}
