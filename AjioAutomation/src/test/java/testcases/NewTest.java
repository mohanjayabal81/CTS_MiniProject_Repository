package testcases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import Utils.ExtentReport;
import Utils.ObjectReader;
import Utils.TakeScreenShot;
import browserImplementation.LaunchBrowser;
import pages.HomePage;
import pages.SelectItem;
import java.util.*;

public class NewTest {
	
	WebDriver driver;
	LaunchBrowser lb;
	ObjectReader obj;
	HomePage h;
	WebDriverWait wait;
	SelectItem select;
	TakeScreenShot image;
	ExtentReports extent;
	ExtentReport e;
	
	@BeforeClass
	public void initialise() throws IOException {
		// Initialize helpers for the browser, report, and screenshots
		lb = new LaunchBrowser();
		obj = new ObjectReader();
		e = new ExtentReport();
		extent = e.generateReport(); // This starts HTML report
		image = new TakeScreenShot();
	}
	
	@Test(priority = 0)
	public void createDriver() throws IOException {
		// Ask the user which browser they want to use
		System.out.println("Select Browser:");
		System.out.println("1. Chrome Browser");
		System.out.println("2. Edge Browser");
		
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		
		switch(choice) {
			case 1: 
				driver = lb.getChromeBrowser();
				break;
			case 2: 
				driver = lb.getEdgeBrowser();
				break;
			default:
				System.out.println("Invalid choice. Starting Chrome by default.");
				driver = lb.getChromeBrowser();
		}
		
		// Open the website and make the window full size
		driver.get(obj.getBaseUrl());
		driver.manage().window().maximize();
		sc.close();
	}
	
	@Test(priority = 1)
	public void validatePage() throws IOException {
		// Make sure we are on the right page by checking the title
		String actTitle = obj.getCurTitle();
		String curTitle = driver.getTitle();
		ExtentTest search = extent.createTest("Check Page Title");
		
		try {
			Assert.assertEquals(actTitle, curTitle);
			String path = image.captureScreenshot(driver, "TitlePage_Pass");
			search.addScreenCaptureFromPath(path);
			search.pass("Title looks good. We are on the right site.");
		}
		catch(AssertionError e) {
			// If it fails, grab a screenshot so we can see what went wrong
			String path = image.captureScreenshot(driver, "TitlePage_Fail");
			search.addScreenCaptureFromPath(path);
			search.fail("Title is wrong. Stopping the test.");
			throw e; // We throw the error so TestNG knows this test failed
		}
	}

  @Test(dependsOnMethods = {"validatePage"})
  public void searchProduct() throws IOException {
	// Navigate to the search bar and verify we can find the products
	  ExtentTest search = extent.createTest("Search for Product");
	  try {
		  h = new HomePage(driver);
		  boolean status = h.searchLocation();
		  Assert.assertTrue(status);
		  
		  String path = image.captureScreenshot(driver, "HomePage_Pass");
		  search.addScreenCaptureFromPath(path);
		  search.pass("Search worked fine.");
	  }
	  catch(AssertionError e) {
		  String path = image.captureScreenshot(driver, "HomePage_Fail");
		  search.addScreenCaptureFromPath(path);
		  search.fail("Could not find the search box.");
		  throw e;
	  }
  }
 
  @Test(dependsOnMethods = {"searchProduct"})
  public void selectAndAddToCart() throws IOException, InterruptedException {
	  // Pick two items and make sure the total price in the cart is correct
	  select = new SelectItem(driver, extent);
	  List<String> priceList = new ArrayList<>();
	  int n = 0;
	  String lastResult = "";
	  
	  // Keep looking through items until we successfully get 2 in the cart
	  for(int i = 1; n < 2; i++) {
		  String result = select.selectItem(i);
		  
		// Stop the test if we can't find any products or their prices at all
		  Assert.assertTrue(!result.equals("noProduct"), "No products Selected");
		  
		  Assert.assertTrue(!result.equals("noPrice"), "Price not found");
		  
		// If the item was added successfully, store the price and move to the next one
		  if(!result.isEmpty()) {
			  priceList.add(result.split("\\.")[0]); 	// Save individual price
			  lastResult = result; 		// Keep track of the final cart total
			  n++; 	// Count this as a success
		  }
		  
	  } 
	  
	  // Add up the prices of the two items
	  int expectedSum = 0;
	  for(String s : priceList) {
		  expectedSum += Integer.parseInt(s);
	  }
	  
	  // Check if our manual total matches with the cart value
	  String actFinalPrice = lastResult.split("\\.")[1].replaceAll("[^0-9]", "");
	  Assert.assertEquals(String.valueOf(expectedSum), actFinalPrice, "Total price in cart is wrong!");
  }
  
  @AfterClass
  public void exitBrowser() throws InterruptedException {
	  // Wait a second, save the report, and close the browser
	  Thread.sleep(1000);
	  extent.flush(); 
	  if (driver != null) {
		  driver.quit();
	  }
  }
}