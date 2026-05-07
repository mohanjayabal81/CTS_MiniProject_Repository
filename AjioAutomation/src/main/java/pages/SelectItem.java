package pages;

import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import Utils.ObjectReader;
import Utils.TakeScreenShot;

public class SelectItem {
	
	WebDriver driver;
	ObjectReader obj;
	Set<String> windows;
	WebDriverWait wait;
	AddToCart add;
	FinalPrice f;
	TakeScreenShot image;
	String numericPrice;
	ExtentReports extent;
	
	public SelectItem(WebDriver driver, ExtentReports extent) throws IOException {
		// Set up the driver and a standard wait time of 10 seconds
	    this.driver = driver;
	    this.extent = extent; 
	    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    
	    // Load helper classes for reading element locators and capturing screenshots
	    this.obj = new ObjectReader(); 
	    this.image = new TakeScreenShot();
	}
	
	public String selectItem(int n) throws IOException, InterruptedException {
		
		// Define the path for the specific item in the search results
	    String itemPath = "(//div[@class='imgHolder'])[" + n + "]//img";
	    String parentWindow = driver.getWindowHandle();
	    
	 // Start a new test entry in the report for the current item
	    ExtentTest t = extent.createTest("Test for Item Number: " + n);
	        
	    try {
	    	
	     // Wait for the product image to be clickable and select it
	        WebElement item = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(itemPath)));
	        item.click();
	        
	     // Capture a screenshot to confirm the click was successful
	        String path = image.captureScreenshot(driver, "SelectImage_Clicked_" + n);
	        t.pass("Clicked item " + n).addScreenCaptureFromPath(path);
	    }
	    catch(Exception e) {
	    	// Log failure if the item cannot be clicked and exit the method
	        String path = image.captureScreenshot(driver, "Fail_Select_" + n);
	        t.fail("Could not click item ").addScreenCaptureFromPath(path);
	        return "noProduct";
	    }
	    
	 // Wait for the browser to open the new product window/tab
	        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
	        
	     // Loop through all open windows to switch focus to the new product tab
	        Set<String> allWindows = driver.getWindowHandles();
	        for (String window : allWindows) {
	            if (!window.equals(parentWindow)) {
	                driver.switchTo().window(window);
	                break;
	            }
	        }
	        
	        // Wait for the page to actually show something 
	        // This ensures the screenshot isn't just a blank white screen
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

	        // Take the screenshot immediately after the switch is confirmed
	        String tabSwitchPath = image.captureScreenshot(driver, "TabSwitched_" + n);
	        t.pass("Successfully switched to the product tab for item " + n)
	         .addScreenCaptureFromPath(tabSwitchPath);
	        
	        try {
	        	// Verify the price element is visible on the details page
	        	WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(obj.getPriceOfItem())));
	        	
	        	// get price of the product
	        	numericPrice = priceElement.getText().replaceAll("[^0-9]", "");

	        }
	        catch(Exception e) {
	        	// If the price isn't found, log the failure and return the error status
	        	String path = image.captureScreenshot(driver, "PriceImage_Fail");
		        t.fail("Could not get price of Product ").addScreenCaptureFromPath(path);
	    		return "noPrice";
	        }
	        
	     // Initialize the AddToCart class and attempt to add the item to the bag
	        add = new AddToCart(driver);   
	        boolean isAdded = add.addTo();

	     // If addition fails (due to being out of stock, etc.), log it and go back to search results
	        if (!isAdded) {
	            String failPath = image.captureScreenshot(driver, "AddToCart_Fail" + n);
	            t.fail("Item " + n + " could not be added to cart (Out of Stock or Button issue)")
	            .addScreenCaptureFromPath(failPath);
	                
	            driver.close();		// Close the current product tab
	            driver.switchTo().window(parentWindow);		// Return to search page
	                
	            return ""; 
	        }

	     // Wait for the URL to change to the cart page to confirm the action worked
	        wait.until(ExpectedConditions.urlContains("cart"));
	            
	     // Take a screenshot of the cart page for the report
	        String cartPath = image.captureScreenshot(driver, "CartPage" + n);
	        t.pass("Item " + n + " successfully added to cart").addScreenCaptureFromPath(cartPath);

	     // Use the FinalPrice class to retrieve the total price from the cart
	        f = new FinalPrice(driver);
	        String totalCartPrice = f.getFinalPrice(n, numericPrice);

	     // Close the cart tab and switch back to the main search results
	        driver.close();
	        driver.switchTo().window(parentWindow);
	            
	     // Return both the individual price and the total cart price for validation
	        return numericPrice + "." + totalCartPrice;
	}

}
