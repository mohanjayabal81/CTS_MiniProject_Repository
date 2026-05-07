package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.utils.ObjectReader;

public class GoogleHomePage {
	WebDriver driver;
	ObjectReader reader = new ObjectReader();
	public GoogleHomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	// return the title of the page
	public String getTitle() {
		String actual = driver.getTitle();
		return actual;
	}
	
	// return a list of all link elements
	public List<WebElement> getAllLinks(){
		return driver.findElements(By.tagName("a"));
	}
	
	// return the search input box element
	public WebElement getSearchBox() {
		return driver.findElement(By.name("q"));
	}
	
	// return the list of search suggestions
	public List<WebElement> getAutoCompleteSuggestions(){
		return driver.findElements(By.xpath(reader.getProperty("suggestions")));
	}
	
	// return the element containing all suggestions
	public WebElement getSearchContainer() {
		return driver.findElement(By.className(reader.getProperty("searchContainer")));
	}
	
	// click on search button by using enter key
	public void clickOnSearchButton() {
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
	}
}
