package pages;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utils.ObjectReader;

public class HomePage {
	WebDriver driver;
	ObjectReader obj;
	WebDriverWait wait;
	WebElement search;
	public HomePage(WebDriver driver) {
		// Set up the driver and a 5-second wait for element visibility
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}
	
	public boolean searchLocation() throws IOException {
		// Load the object reader to fetch element locator
		obj = new ObjectReader();
		
		// Wait for the search input field to appear on the page before interacting
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(obj.getSearchLocation())));
		
		// Check if the search field is currently visible to the user
		boolean status = driver.findElement(By.xpath(obj.getSearchLocation())).isDisplayed();
		
		// Identify the search element and enter the product name
		search = driver.findElement(By.xpath(obj.getSearchLocation()));
		search.sendKeys(obj.getSearchItem());
		
		// Simulate pressing the 'Enter' key to trigger the search action
		search.sendKeys(Keys.ENTER);
		
		// Return the visibility status for validation in the test case
		return status;
	}
	

}
