
package tests;

import java.io.IOException;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import browsers.BrowserImplementation;
import config.ObjectReader;
import pages.ForgotPasswordPage;
import pages.LandingPage;
import pages.PrivacyPolicyPage;
import pages.RediffLoginPage;

public class TestAlert {
	//declaring objects of classes
	LandingPage landingpage;
	RediffLoginPage rediffloginpage;
	ForgotPasswordPage forgotpasswordpage;
	PrivacyPolicyPage privacypolicypage;
	
	WebDriver driver;
	BrowserImplementation browser;
	ObjectReader reader;

	@Parameters("browser")
	@BeforeTest
	public void setup(String browserName) throws IOException {
		
		//creating objects
		browser = new BrowserImplementation();
		reader = new ObjectReader();
		
		driver = browser.getBrowser(browserName);
		driver.get(reader.getBaseUrl());
		

		//Initializing Pages
		landingpage = new LandingPage(driver);
		rediffloginpage = new RediffLoginPage(driver);
		forgotpasswordpage = new ForgotPasswordPage(driver);
		privacypolicypage = new PrivacyPolicyPage(driver);
	}
	
	
	@Test(priority = 0)
	public void verifyLandingPage() {
		try {
			String actLandingCheck=landingpage.verifyLandingPage();
			System.out.println(actLandingCheck);
			String expected=reader.getLandingCheck();
			Assert.assertEquals(actLandingCheck,expected);
			landingpage.clickSignIn();
		}
		catch(Exception e) {
			Assert.fail("Unable to load landing page");
		}
		
	}
	
	//Test Login Alert
	@Test(priority = 1)
	public void testLoginAlert() {
		try {
			String actLoginAlert = rediffloginpage.clickLoginAndGetAlert();
			String expected=reader.getLoginAlert();
			
			//Hard Assert
			Assert.assertEquals(actLoginAlert,expected);
		}
		catch(Exception e) {
			Assert.fail("Unable To Verify Login Alert.");
		}
	}

	//Test Forgot Password Alert
	@Test(priority = 2)
	public void testForgotPasswordAlert() {
		rediffloginpage.clickForgotPassword();
		try {
			
			String actPasswordAlert = forgotpasswordpage.clickNextAndGetAlert();
			String expected=reader.getPasswordAlert();
			
			//Hard Assert
			Assert.assertEquals(actPasswordAlert,expected);
		}
		catch(Exception e) {
			Assert.fail("Unable To Verify Forgot Pasword Alert.");
		}
	}

	//Verify Privacy-Policy Page Opened or Not
	@Test(priority = 3)
	public void testPrivacyPolicy() {
		try {
			String actPrivacyText = privacypolicypage.getPrivacyPolicyTitle();
			String expPrivacyText = reader.getTitlePrivacyPage();
		
		//Hard Assert
			Assert.assertEquals(actPrivacyText,expPrivacyText);
		}
		catch(Exception e) {
			Assert.fail("Unable To Verify Privacy-Policy Opened or Not.");
		}
		

	}

	//Quitting Browser 
	@AfterTest
	public void exitBrowser() {
		if (driver != null) {
			driver.quit();
		}
	}
}
