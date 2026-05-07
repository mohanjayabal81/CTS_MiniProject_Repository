package pages;

import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import configReader.ObjectReader;

public class SearchProductPage {
	
	WebDriver driver;
	ObjectReader or;
	FluentWait<WebDriver> wait;
	JavascriptExecutor js;
	
	//Constructor
	public SearchProductPage(WebDriver driver)  {
		this.driver=driver;
		or=new ObjectReader();
		
		//set a timeout of 10 seconds
		this.wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(15))
														.pollingEvery(Duration.ofSeconds(2))
														.ignoring(NoSuchElementException.class);
		js=(JavascriptExecutor)driver;
	}
	
	//Verify search Product page based on criteria
	public String verify_Search_Product_Criteria() throws InterruptedException {
		
		//xpath to get text for verification
		String searchTextVerifyXpath=or.get_searchTextVerify();
		
		Thread.sleep(2000);
		//Webelement to get the verify search criteria
		WebElement searchVerifyElement=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(searchTextVerifyXpath)));
		
		//Get text from webelement
		String searchVerifyElemnt=searchVerifyElement.getText();
		
		//return the String
		return searchVerifyElemnt;
		
	}
	
	//To display filter section
	public void diplay_Filters() throws InterruptedException {
		
		//xpath to get filters display
		String to_display_Filters_Xpath=or.get_TodisplayFilters();
		
		//click to display filters
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(to_display_Filters_Xpath))).click();
	}
	
	
	//In filter section get the max and set it max as required
	public void max_filter_get_and_set() throws InterruptedException {
		
		//implicitly wait at the start
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		//To go to maxfilter section
		String to_find_priceMaxFilter=or.scroll_toMaxElement();
		WebElement priceMaxFilterdriver_element=driver.findElement(By.xpath(to_find_priceMaxFilter));
		
		//scroll to view maxfilter selecting
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);", priceMaxFilterdriver_element);
		
		//select and set the max price
		WebElement maxElement=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(or.get_MaxElement())));
		
		//Create a select object
		Select select=new Select(maxElement);
		
		//wait until options are present
		wait.until(driver -> select.getOptions().size() > 1);
		
		//select value
		select.selectByVisibleText("₹10000");
	
	}
	
	
	//To set the OS Version
	public void os_version_fetch() throws InterruptedException {
		
		Thread.sleep(3000);
		//To scroll To Operating system visibility
		WebElement to_scrollToOperatingSystem=
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(or.get_scrollToOperatingSystem())));
		js.executeScript("arguments[0].scrollIntoView(true);", to_scrollToOperatingSystem);
		
		Thread.sleep(3000);
		//To select Operating System version
		WebElement osversion=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(or.get_selectOsVersion())));
		osversion.click();
		
	}
	
	//To select pie version
	public void pie_version_select() throws InterruptedException {
		
		Thread.sleep(1000);
		//To make visible of more Operating system versions
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(or.get_TovisibleMoreVersions()))).click();

		//scroll to make Pie element visible
		WebElement to_scrollToMakePieVisible=
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(or.get_scrollToMakePieVisible())));
		js.executeScript("arguments[0].scrollIntoView(true);", to_scrollToMakePieVisible);
		
		Thread.sleep(1000);
		//To Select version Pie
		WebElement pie=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(or.get_versionPie())));
		pie.click();
	}
	
	//To select newest arrival
	public void new_arrivals_fetch() throws InterruptedException {
		
		Thread.sleep(1000);
		//to get newest arrivals of mobiles
		WebElement ele = driver.findElement(By.xpath(or.get_newestArrivalPath()));
		ele.click();
		
		
	}
}
