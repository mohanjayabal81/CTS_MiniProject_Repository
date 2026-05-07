package pages;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utils.ObjectReader;

public class FinalPrice {
	WebDriver driver;
	WebDriverWait wait;
	ObjectReader obj;
	public FinalPrice(WebDriver driver) {
		// Initialize the driver and set a 5-second wait for the cart elements to load
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}
	
	public String getFinalPrice(int n, String price) throws IOException {
		obj = new ObjectReader();
		// Wait until the final price element is visible in the cart
		 WebElement finalPriceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(obj.getFinalPrice())));
		 
		// Extract the text, remove decimals, and strip away any non-numeric characters
	     String totalCartPrice = finalPriceElement.getText().split("\\.")[0].replaceAll("[^0-9]", "");
	        
	  // Print a success message
	     System.out.println("Success! Item " + n + " Price :  " + price + "|" + "Cart Total: " + totalCartPrice);
	     
	     // Return the cleaned-up total price for final validation
	     return totalCartPrice;
	}

}
