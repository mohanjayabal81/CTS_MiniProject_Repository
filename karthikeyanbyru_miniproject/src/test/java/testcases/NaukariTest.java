package testcases;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import browserimplementation.BrowserConfigReader;
import configreader.ObjectReader;
import pages.JobDescriptionPage;
import pages.LandingPage;
import utilities.ScreenShotUtil;
public class NaukariTest {
	WebDriver driver;
	BrowserConfigReader browserConfig;
	LandingPage landingPage;
	JobDescriptionPage jobDescriptionPage;
	ObjectReader objectreader;
	// Test Data
	private static final String MAINPAGETITLE = "Jobs - Recruitment - Job Search - Employment - Job Vacancies - Naukri.com";
	private static final String KEYWORD = "web development fresher";
	private static final String LOCATION = "Pune";
	private static final String EXP_RESULT = "Web Development Fresher Jobs In Pune";
	private static final String EXP_EXP_VAL = "1";
	@BeforeClass
	public void setUp() throws IOException {
		browserConfig = new BrowserConfigReader();
		driver = browserConfig.setUpBrowser();
		objectreader = new ObjectReader();
		driver.get(objectreader.getUrl());
		landingPage = new LandingPage(driver);
		jobDescriptionPage = new JobDescriptionPage(driver);
	}
	// ── Test 1: Verify Page Title ─────────────────────────────────────────────
	@Test(priority = 1)
	public void testLandingPage() {
		try {
			String acTitle = landingPage.getPageTitle();
			Assert.assertEquals(acTitle, MAINPAGETITLE, "Page title mismatch!");
			Reporter.log("The page title has been verified.");
		} catch (Exception e) {
			ScreenShotUtil.capture(driver, "testLandingPage_ERROR");
			Assert.fail(e.getMessage());
		}
	}
	// Test 2: Enter search details
	@Test(priority = 2)
	public void testEnterDetails() {
		try {
			landingPage.enterKeyword(KEYWORD);
			landingPage.enterLocation(LOCATION);
			landingPage.selectExperience();
			landingPage.clickSearch();
			Reporter.log("Search details entered successfully.", true);
		} catch (Exception e) {
			ScreenShotUtil.capture(driver, "testEnterDetails_FAIL");
			Reporter.log("FAILED: " + e.getMessage(), true);
			Assert.fail(e.getMessage());
		}
	}
	// Test 3: Verify results header and experience value
	@Test(priority = 3)
	public void testVerifyResultsAndExperience() {
		try {
			// Verify results header
			String actualHeader = jobDescriptionPage.getResultsHeaderText(EXP_RESULT);
			Assert.assertEquals(actualHeader, EXP_RESULT);
			Reporter.log("Results header verified.", true);
			// Verify experience value
			String actualExp = jobDescriptionPage.getResultsExperienceValue(EXP_EXP_VAL);
			Assert.assertEquals(actualExp, EXP_EXP_VAL);
			Reporter.log("Results and experience verified successfully.", true);
		} catch (AssertionError | Exception e) {
			ScreenShotUtil.capture(driver, "testVerifyResultsAndExperience_FAIL");
			Reporter.log("Test 2 FAILED: " + e.getMessage(), true);
			Assert.fail(e.getMessage());
		}
	}
	@AfterClass
	public void tearDown() {
		browserConfig.closeBrowser();
		Reporter.log("Browser closed.", true);
	}
}