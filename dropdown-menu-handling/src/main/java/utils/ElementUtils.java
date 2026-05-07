package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementUtils {
	
	WebDriver driver;
	
	public ElementUtils(WebDriver driver) {
		this.driver = driver;
	}
	
	// Check if element is enabled
	public boolean isElementEnabled(By locator) {
		return driver.findElement(locator).isEnabled();
	}
	
	// Check if element is displayed
	public boolean isElementDisplayed(By locator) {
		return driver.findElement(locator).isDisplayed();
	}
	
	// Check if element is selected
	public boolean isElementSelected(WebElement element) {
		return element.isSelected();
	}
}
 