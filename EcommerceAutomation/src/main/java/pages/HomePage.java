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
	
	WebDriver driver;
	WebDriverWait wait;
	ObjectReader or;
	
	//path to verify home page
	By homePagePath=By.xpath("(//img[@alt='Image'])[1]");
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		or=new ObjectReader();
		//set a timeout of 5 seconds
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}
	
	//Verify home page via logo image displayed or not
	public boolean Verify_Home_Page() {
		try {
			WebElement home_page_logo=wait.until(ExpectedConditions.presenceOfElementLocated(homePagePath));
			return home_page_logo.isDisplayed();
			}
		catch(Exception e) {
			return false;
		}
	}
	
	//Search for the item which is required 
	public void search_product() {
		
		//Get the Xpath of searchBox from Object repository
		String sBox=or.get_SearchBox();
		
		//Find the searchBox and Send Text
		WebElement searchBox=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sBox)));
		searchBox.sendKeys("mobiles under 15000");
		
		//Get the xpath of displayed text of mobile under 15000
		String mobilesXpath=or.get_MobileUnder15000Xpath();
		
		try {
		//Find element of mobilesXpath and click
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(mobilesXpath))).click();
		}catch(Exception e) {
			searchBox.sendKeys(Keys.ENTER);
		}
	}
}
