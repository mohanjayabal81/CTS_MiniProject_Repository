package com.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.pages.GoogleSearchResultPage;
import com.utils.TestUtilities;

public class SearchResultTests {

	WebDriver driver = HomePageTests.driver;
	GoogleSearchResultPage resultsPage = new GoogleSearchResultPage(driver);
	TestUtilities utils = new TestUtilities();
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	ExtentReports extent = HomePageTests.extent;
	ExtentTest test;

	@Test
	public void VerifyResultPage() {
		test = HomePageTests.extent.createTest("VerifyResultPage");
		String expected = "Cognizant - Google Search";
		String actual = resultsPage.getResultPageTitle();
		System.out.println("Result Page Title : " + actual);
		Assert.assertEquals(actual, expected);
		test.pass("Result page title verified: " + actual);
	}

	@Test(dependsOnMethods = "VerifyResultPage")
	public void AllTabTest() throws IOException, InterruptedException {
		// Click the All tab and wait for the page to load
		test = HomePageTests.extent.createTest("AllTabTest");
		resultsPage.clickOnAllTab();
		wait.until(ExpectedConditions.titleContains("Cognizant"));

		System.out.println("---- All Tab ----");

		int counter = TestUtilities.screenshotCounter;
		utils.takeFullPageScreenshot("AllTab", driver);
		Assert.assertTrue(new File("screenshots/" + counter + "_AllTab.png").exists(), "All tab screenshot not saved");
		test.pass("All tab navigated and screenshot captured successfully");
	}

	@Test(dependsOnMethods = "AllTabTest")
	public void NewsTabTest() throws IOException, InterruptedException {
		// Click the News tab and wait for page to load
		test = HomePageTests.extent.createTest("NewsTabTest");
		resultsPage.clickOnNewsTab();
		wait.until(ExpectedConditions.titleContains("Cognizant"));

		System.out.println("---- News Tab ----");

		int counter = TestUtilities.screenshotCounter;
		utils.takeFullPageScreenshot("NewsTab", driver);
		Assert.assertTrue(new File("screenshots/" + counter + "_NewsTab.png").exists(),
				"News tab screenshot not saved");
		test.pass("News tab navigated and screenshot captured successfully");
	}

	@Test(dependsOnMethods = "NewsTabTest")
	public void ImagesTabTest() throws IOException, InterruptedException {
		// Click the Images tab and wait for page to load
		test = HomePageTests.extent.createTest("ImagesTabTest");
		resultsPage.clickOnImagesTab();
		wait.until(ExpectedConditions.titleContains("Cognizant"));

		System.out.println("---- Images Tab ----");

		int counter = TestUtilities.screenshotCounter;
		utils.takeFullPageScreenshot("ImagesTab", driver);
		Assert.assertTrue(new File("screenshots/" + counter + "_ImagesTab.png").exists(),
				"Images tab screenshot not saved");
		test.pass("Images tab navigated and screenshot captured successfully");
	}

	@Test(dependsOnMethods = "ImagesTabTest")
	public void VideosTabTest() throws IOException, InterruptedException {
		// Click the Videos tab and wait for page to load
		test = HomePageTests.extent.createTest("VideosTabTest");
		resultsPage.clickOnVideosTab();
		wait.until(ExpectedConditions.titleContains("Cognizant"));

		System.out.println("---- Videos Tab ----");

		int counter = TestUtilities.screenshotCounter;
		utils.takeFullPageScreenshot("VideosTab", driver);
		Assert.assertTrue(new File("screenshots/" + counter + "_VideosTab.png").exists(),
				"Videos tab screenshot not saved");
		test.pass("Videos tab navigated and screenshot captured successfully");

	}

}
