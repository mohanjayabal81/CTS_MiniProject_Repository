package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utils.ObjectReader;

public class GoogleSearchResultPage {
	WebDriver driver;
	ObjectReader reader = new ObjectReader();

	// all the locators used in the Search Results Page
	private By allTab    = By.xpath(reader.getProperty("allTab"));
	private By newsTab   = By.xpath(reader.getProperty("newsTab"));
	private By imagesTab = By.xpath(reader.getProperty("imagesTab"));
	private By videosTab = By.xpath(reader.getProperty("videosTab"));

	public GoogleSearchResultPage(WebDriver driver) {
		this.driver = driver;
	}
	
	// return the result page title
	public String getResultPageTitle() {
		return driver.getTitle();
	}

	// click on the all tab 
	public void clickOnAllTab() {
		driver.findElement(allTab).click();
	}

	// click on the news tab
	public void clickOnNewsTab() {
		driver.findElement(newsTab).click();
	}

	// click on the Images tab
	public void clickOnImagesTab() {
		driver.findElement(imagesTab).click();
	}

	// click on the videos tab
	public void clickOnVideosTab() {
		driver.findElement(videosTab).click();
	}
}

