package pages;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import configReader.ObjectReader;

public class HomePage {

	private WebDriver driver;
	private ObjectReader objectReader;
	
	By searchBoxInput = By.id("search-box-input");
	private int duration=10;

	public HomePage(WebDriver driver) throws IOException {
		
		// Initializing WebDriver instance
		this.driver = driver;
		
		objectReader= new ObjectReader();
	}
	
	// returns current URL from webPage
	public String getCurrentHomePageUrl() {
		String actualUrl= driver.getCurrentUrl();
		return actualUrl;
	}
	
	// return current homePage title
	public String getCurrentHomePageTitle() {
		String actualTitle = driver.getTitle();
		return actualTitle;
 	}
	
	public void searchProduct()  {
		
		// Initializes WebDriverWait with a maximum timeout of 10 seconds
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(duration));
		
		WebElement searchBox =wait.until(ExpectedConditions.visibilityOfElementLocated((searchBoxInput)));
		
		// Sending the Search Item name
		searchBox.sendKeys(objectReader.getSearchItemName());
		
		// Trigger Enter key action on the search box
		searchBox.sendKeys(Keys.ENTER);
	}
	
	// returning the searchBox
	public WebElement getSearchBox() {
		return driver.findElement(searchBoxInput);
	}

}
