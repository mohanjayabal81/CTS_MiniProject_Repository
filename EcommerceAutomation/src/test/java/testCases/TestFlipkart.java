package testCases;

import java.io.IOException;
import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import browserImplementation.BrowserConfiguartion;
import configReader.ObjectReader;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductRetrievePage;
import pages.SearchProductPage;
import utils.ExtentReport;
import utils.ReusableMethod;

public class TestFlipkart {
		WebDriver driver;
		BrowserConfiguartion br;
		ObjectReader or;
		HomePage hp;
		LoginPage lp;
		ProductRetrievePage prp;
		SearchProductPage spp;
		SoftAssert soft;
		ReusableMethod rm;
		ExtentReport er;
		ExtentReports extent;
		
		public TestFlipkart() throws IOException {
			
			or=new ObjectReader();
			
			//using soft assertion
			soft=new SoftAssert();
			
			//instance of ExtentReport class
			er=new ExtentReport();
			
		}
		@BeforeTest
		public void setUp() throws IOException {
			
			rm=new ReusableMethod(driver);
			
			//calling the method for extent report instance
			extent=er.reportGenerator();
			
			//initialize scanner
			Scanner sc=new Scanner(System.in);
			
			//Browser instantiation
			br=new BrowserConfiguartion();
			driver=br.Launch_Chrome_Browser();
						
			//Get url from object.properties file
			String url=or.get_BaseUrl();
			
			//Opening URL in browser
			driver.get(url);
			
			//Maximize the window
			driver.manage().window().maximize();
			sc.close();
		
		}
		
		@Test(priority=1)
		public void LoginPage_Test() throws IOException {
			
			lp=new LoginPage(driver);
			
			ExtentTest loginPage=extent.createTest("Login_Page_Test");
			
			//get url and verify
			String act_url=lp.get_Current_Url();
			String exp_url=or.getExpectedUrl();
			
			//To take screenshot if it fails
			try {
			Assert.assertEquals(act_url, exp_url);
			
			//taking screen shot
			String ssPath=rm.takeScreenshot(driver,"url_login_pass");
			loginPage.addScreenCaptureFromPath(ssPath);
			loginPage.pass("Verified_url");
			}
			catch(Throwable e) {
				//taking screen shot
				String ssPath=rm.takeScreenshot(driver,"url_fail");
				loginPage.addScreenCaptureFromPath(ssPath);
				loginPage.fail("Verified_url_failed");
				throw e;
			}
			
			//Verify the login page using soft assertion
			boolean bool=lp.verify_LoginPage();
			soft.assertTrue(bool);
			soft.assertAll();
			
			//close the popup
			lp.closePopup();
			
		}
		
		@Test(dependsOnMethods= {"LoginPage_Test"})
		public void HomePage_Test() throws InterruptedException, IOException {
			
			hp=new HomePage(driver);
			
			ExtentTest homePage=extent.createTest("Home_Page_Test");
			
			try {
			//verify the HomePage
			boolean bool=hp.Verify_Home_Page();
			Assert.assertTrue(bool);
			
			//taking screen shot
			String ssPath=rm.takeScreenshot(driver,"Homepage_pass");
			homePage.addScreenCaptureFromPath(ssPath);
			homePage.pass("Verified_home_page");
			}
			catch(Throwable e) {
				//taking screen shot
				String ssPath=rm.takeScreenshot(driver,"Homepage_fail");
				homePage.addScreenCaptureFromPath(ssPath);
				homePage.fail("HomePage_verification_Failed");
				throw e;
			}
			
			//Search for the product
			hp.search_product();
			
		}
		
		@Test(dependsOnMethods= {"HomePage_Test"})
		public void SearchProductPage_Test() throws IOException, InterruptedException {
			
			spp=new SearchProductPage(driver);
			
			ExtentTest searchPage=extent.createTest("Search_Page_Test");
			
			try {
				
			//verify search product criteria
			String actText_Search=spp.verify_Search_Product_Criteria();
			String expText_Search=or.get_expected_search_Verify_Text();
			Assert.assertEquals(actText_Search, expText_Search);
			
			//take screen shot
			String ssPath=rm.takeScreenshot(driver,"search_criteria");
			searchPage.addScreenCaptureFromPath(ssPath);
			searchPage.pass("searchPage_verification_passed");
			
			}catch(Throwable e) {
				//take screen shot
				String ssPath=rm.takeScreenshot(driver,"search_criteria_mismatch");
				searchPage.addScreenCaptureFromPath(ssPath);
				searchPage.fail("searchPage_verification_Failed");
				
				throw e;
			}
			
			//To display Filters
			spp.diplay_Filters();
			
			//To set the max 
			spp.max_filter_get_and_set();
			
			//To fetch os version
			spp.os_version_fetch();
			
			//os version select pie
			spp.pie_version_select();
			
			//To set the newest arrivals
			spp.new_arrivals_fetch();
			
		}
		
		@Test(dependsOnMethods= {"SearchProductPage_Test"})
		public void ProductRetrievePage_Test() throws IOException {
			
			ExtentTest retrievePage=extent.createTest("ProductRetrieve_Page_Test");
			
			prp=new ProductRetrievePage(driver);
			
			try {
			//To verify price of first mobile
			int price_verify=prp.verify_firstMobile_PriceCriteria();
			Assert.assertTrue(price_verify<30000);
			
			//take screen shot
			String ssPath=rm.takeScreenshot(driver,"Mobile_retieve_page");
			retrievePage.addScreenCaptureFromPath(ssPath);
			retrievePage.pass("product_retrieved");
			}
			catch(Throwable e) {
				//take screen shot
				String ssPath=rm.takeScreenshot(driver,"mobile_wrong_displayed");
				retrievePage.addScreenCaptureFromPath(ssPath);
				retrievePage.fail("product_retrieved_failed");
				throw e;
			}
			
			//To display mobiles
			prp.mobiles_fetching_underCriteria();
			
			
		}
		
		@AfterTest
		public void exitBrowser() {
			extent.flush();
			driver.quit();
		}
		
		
}
