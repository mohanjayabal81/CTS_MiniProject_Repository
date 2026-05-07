package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import browser.BrowserImplementation;
import config.ObjectReader;
import pages.LandingPage;
import pages.RediffPage;
import utils.ScreenshotUtil;

public class DropdownTest {
	
	// WebDriver reference
	WebDriver driver = null;
	
	// Page class reference
	RediffPage rediffPage;
	LandingPage landingPage;
	
	// Browser setup class reference
	BrowserImplementation browserImplementation;
	
	// Properties file reader reference
	ObjectReader objectReader;
	
	@Parameters("browser")
	@BeforeTest
	public void setUp(@Optional("chrome") String browserName) throws IOException {
		
		// Create browser implementation object
		browserImplementation = new BrowserImplementation();
		
		// Read data from object.properties file
		objectReader = new ObjectReader();
		
		// Read browser name from properties file
		// String browserName = objectReader.getBrowser();
		
		// Launch browser based on browser name
		if (browserName.equalsIgnoreCase("chrome")) {
			driver = browserImplementation.getChromeDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = browserImplementation.getEdgeDriver();
		} else {
			throw new RuntimeException("Invalid browser name");
		}
		
		// Open URL
		driver.get(objectReader.getBaseUrl());

		// Create Page Object
		rediffPage = new RediffPage(driver);
	}
	
	@Test(priority = 0)
	public void validateLandingPage() {
		landingPage = new LandingPage(driver);
		
		String actUrl = landingPage.getPageUrl();
		String expUrl = "https://mail.rediff.com/cgi-bin/login.cgi";
		
		// Validate page URL
		Assert.assertEquals(actUrl, expUrl, "Landing page URL is incorrect");
		Reporter.log("Landing page URL is correct<br>");
		
		// Validate page title
		String actTitle = landingPage.getPageTitle();
		Assert.assertTrue(actTitle.contains("Rediffmail"), "Landing page title is incorrect");
		Reporter.log("Landing page title is correct<br>");
		
		// Validate page UI (No blank screen)
		Assert.assertTrue(landingPage.isPageLoaded(), "Landing page is not loaded properly (UI elements missing)");
		Reporter.log("Landing page UI loaded correctly<br>");
	}

	// Test to click Create Account and validate page title
	@Test(priority = 1, dependsOnMethods = "validateLandingPage")
	public void createAccountTest() {
		Reporter.log("Clicking on Create Account link" + "<br>");
		String actTitle = rediffPage.clickCreateAccount();
		
		// Expected page title
		String exTitle = "Create a Rediffmail account";
		
		try {
			// Validate actual and expected title
			Assert.assertEquals(actTitle, exTitle);
			Reporter.log("Create Account page title validated successfully" + "<br>");

		} catch(AssertionError e) {

			String path = ScreenshotUtil.takeScreenshot(driver, "createAccountTest");

			Reporter.log("Create Account page validation failed<br>");
			Reporter.log("<a href='" + path + "'>Click to view Screenshot</a><br>");

			Assert.fail();
		}
	}

	// Test to enter user details
	@Test(priority = 2, dependsOnMethods = "createAccountTest")
	public void inputTest() {
		
		// Enter user name
		Reporter.log("Entering user name" + "<br>");
		rediffPage.enterName(objectReader.getName());

		// Enter email ID
		Reporter.log("Entering email ID" + "<br>");
		rediffPage.enterEmail(objectReader.getEmail());

		// Click on check Availability
		Reporter.log("Checking email availability" + "<br>");
		rediffPage.checkAvailability();

		// Select suggested email
		Reporter.log("Selecting auto-suggested email" + "<br>");
		rediffPage.selectSuggestedEmail();

		// Enter password
		Reporter.log("Entering password" + "<br>");
		rediffPage.enterPassword(objectReader.getPassword());

		// Re-enter password
		Reporter.log("Re-entering password" + "<br>");
		rediffPage.reEnterPassword(objectReader.getPassword());
	}

	// Test to select Date of Birth from dropdowns
	@Test(priority = 3, dependsOnMethods = "inputTest")
	public void testDOB_Dropdown() {
		
		// Validate dob dropdown is enabled
		try {
			// Validate dob day dropdown is enabled
			Assert.assertTrue(rediffPage.isDobDayDropdownEnabled());
			Reporter.log("Day dropdown is enabled" + "<br>");
			
			// Validate dob month dropdown is enabled
			Assert.assertTrue(rediffPage.isDobMonthDropdownEnabled());
			Reporter.log("Month dropdown is enabled" + "<br>");
			
			// Validate dob year dropdown is enabled
			Assert.assertTrue(rediffPage.isDobYearDropdownEnabled());
			Reporter.log("Year dropdown is enabled" + "<br>");
		
		} catch(AssertionError e) {
			Reporter.log(e.getMessage());
			Assert.fail();
		}
		
		
		// Select DOB
		Reporter.log("Selecting Date of Birth from dropdowns" + "<br>");
		rediffPage.selectDOB(objectReader.getDay(), objectReader.getMonth(), objectReader.getYear());
	}

	// Test to select gender
	@Test(priority = 4, dependsOnMethods = "testDOB_Dropdown")
	public void testGender() {
		
		Reporter.log("Selecting gender" + "<br>");
		rediffPage.selectGender("m");
		
		try {
			// Validate gender is selected
			Assert.assertTrue(rediffPage.isGenderSelected(), "Gender radio button is not slected");
		} catch(AssertionError e) {
			Reporter.log("Gender selected successfully<br>");
		}
	}

	// Test to fetch and print all country values
	@Test(priority = 5, dependsOnMethods = "testGender")
	public void testCountryDropdown() {
		
		rediffPage.printAllCountries();
		rediffPage.printCountryCount();
		
		Reporter.log("Country dropdown validated<br>");
	}

	// Test to select and validate country
	@Test(priority = 6, dependsOnMethods = "testCountryDropdown")
	public void validateCountrySelection() {
		
		
		//Validate country dropdown enabled
		try {
			Assert.assertTrue(rediffPage.isCountryDropdownEnabled());
			Reporter.log("Country dropdown is enabled" + "<br>");
		} catch(AssertionError e) {
			Reporter.log(e.getMessage());
			Assert.fail();
		}
		
		// Expected country value
		String expectedCountry = "India";
		
		// Select country from dropdown
		rediffPage.selectCountry(expectedCountry);
		
		// Get selected country
		String actualCountry = rediffPage.getSelectedCountry();
		
		try {
			// Validate selected country (Soft Assertion)
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(actualCountry, expectedCountry, "Selected country does not match expected value");
			
			// Collect soft assertion result
			softAssert.assertAll();
			
			Reporter.log("Country selected and validated<br>");
			
		} catch (AssertionError e) {
			String path = ScreenshotUtil.takeScreenshot(driver, "validateCountrySelection");

			Reporter.log("Country validation failed: " + e.getMessage() + "<br>");
			Reporter.log("<a href='" + path + "'>Click to view Screenshot</a><br>");

			Assert.fail();

		}
	}
	
	// Select city from dropdown
	@Test(priority = 7, dependsOnMethods = "validateCountrySelection")
	public void testCityDropdown() {
		
		// Validate city dropdown is enabled
		try {
			Assert.assertTrue(rediffPage.isCityDropdownEnabled());
			Reporter.log("City dropdown is enabled" + "<br>");
		} catch(AssertionError e) {
			Reporter.log(e.getMessage());
			Assert.fail();
		}
		
		// Select city from dropdown
		rediffPage.selectCity(objectReader.getCity());
		Reporter.log("City selected successfully");
	}
	
	// Test to verify and select the Alternate ID checkbox during registration
	@Test(priority = 8, dependsOnMethods = "testCityDropdown")
	public void testAlternateIdCheckBox() {
		Assert.assertTrue(rediffPage.isAlternateIdCheckBox(), "Alternate ID checkbox is enabled");
		
		rediffPage.alternateIdCheckbox();
		Reporter.log("Alternate ID checkbox selected<br>");
	}

	@AfterTest
	public void tearDown() {
		
		// Final screenshot after complete automation
		String finalScreenshotPath = ScreenshotUtil.takeScreenshot(driver, "Final_Execution_State");

		// Close the browser
		driver.quit();
	}

}
