package com.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.browsersImplementation.BrowsersAvailable;
import com.pages.GoogleHomePage;
import com.utils.ObjectReader;
import com.utils.TestUtilities;

public class HomePageTests {

	static BrowsersAvailable browser = new BrowsersAvailable();
	static WebDriver driver = browser.launchChorme();
	static ExtentReports extent;
	static ExtentTest test;
	TestUtilities utils = new TestUtilities();
	GoogleHomePage homePage = new GoogleHomePage(driver);
	ObjectReader reader = new ObjectReader();
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	SoftAssert softassert = new SoftAssert();

	@BeforeSuite
	public void setupReports() {
		ExtentSparkReporter spark = new ExtentSparkReporter("reports/ExtentReport.html");
		spark.config().setReportName("Google Automation Report");
		spark.config().setDocumentTitle("Test Results");
		extent = new ExtentReports();
		extent.attachReporter(spark);
	}

	@Test
	public void VerifyHomePage() {
		// checks if the HomePage is correctly opened
		test = HomePageTests.extent.createTest("VerifyHomePage");
		String actualTitle = homePage.getTitle();
		String expectedTitle = reader.getProperty("expectedTitle");

		Assert.assertEquals(actualTitle, expectedTitle);

		test.pass("Home page title verified successfully");

	}

	@Test(dependsOnMethods = "VerifyHomePage")
	public void NoOfLinksInWebPage() {
		// prints the number of links with their name
		test = HomePageTests.extent.createTest("NoOfLinksInWebPage");
		List<WebElement> links = homePage.getAllLinks();
		System.out.println("No of Links on the Home Page : " + links.size());

		for (int i = 1; i <= links.size(); i++) {
			WebElement link = links.get(i - 1);
			System.out.println("Link " + i + ":" + link.getText());
		}

		try {
			Assert.assertTrue(links.size() > 0, "No Links Found On the Home Page");
			test.pass("Total links found: " + links.size());
		} catch (AssertionError e) {
			test.fail("Total links found: " + links.size());

		}
	}

	@Test(dependsOnMethods = "NoOfLinksInWebPage")
	public void VerifySearchText() {
		// checks if the text enter in the searchbox matches the expected text
		test = HomePageTests.extent.createTest("VerifySearchText");
		WebElement searchBox = homePage.getSearchBox();
		searchBox.sendKeys("Cognizant");
		softassert.assertEquals(searchBox.getAttribute("value"), "Cognizant");
		softassert.assertAll();
		test.pass("Search box text verified successfully");
	}

	@Test(dependsOnMethods = "VerifySearchText")
	public void ScreenshotOfSearchSuggestion() throws IOException {
		// takes the screenshot of the search suggestions displayed

		// wait until search box appears

		test = HomePageTests.extent.createTest("ScreenshotOfSearchSuggestion");
		wait.until(ExpectedConditions.visibilityOf(homePage.getSearchContainer()));

		WebElement searchSuggestionContainer = homePage.getSearchContainer();
		int counter = TestUtilities.screenshotCounter;
		utils.takeScreenshotOfElement("searchSuggestions", searchSuggestionContainer);
		File screenshot = new File("screenshots/" + counter + "_searchSuggestions" + ".png");

		try {
			Assert.assertTrue(screenshot.exists(), "Taking screenshot failed !!!");
			test.pass("Search suggestion screenshot captured successfully");
		} catch (AssertionError e) {
			test.fail("Search suggestion screenshot captured Failed");

		}

	}

	@Test(dependsOnMethods = "ScreenshotOfSearchSuggestion")
	public void NoOfSearchSuggestionDisplayed() throws InterruptedException {
		// prints the suggestions displayed

		test = HomePageTests.extent.createTest("NoOfSearchSuggestionDisplayed");
		List<WebElement> suggestions = homePage.getAutoCompleteSuggestions();

		for (int i = 1; i <= suggestions.size(); i++) {
			WebElement suggestion = suggestions.get(i - 1);
			System.out.println("suggestions " + i + ":" + suggestion.getText());
		}

		softassert.assertTrue(suggestions.size() > 0, "No suggestions Found On the Home Page");
		softassert.assertAll();
		test.pass("Total suggestions found: " + suggestions.size());
		homePage.clickOnSearchButton();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(reader.getProperty("allTab"))));
	}

	@AfterSuite
	public void tearDownReport() {
		System.out.println("---- Closing Browser ----");

		extent.flush();
		driver.quit();
	}

}
