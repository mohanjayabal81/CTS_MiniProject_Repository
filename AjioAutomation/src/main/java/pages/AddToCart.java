package pages;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utils.ObjectReader;

public class AddToCart {
	WebDriver driver;
	WebDriverWait wait;
	ObjectReader obj;
	public AddToCart(WebDriver driver) {
		// Initialize the driver and set a 10-second wait for element synchronization
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	public boolean addTo() throws IOException, InterruptedException {
		// Load the object reader to access the element locators
		obj = new ObjectReader();
		
		// First, check if the product is marked as 'Out of Stock' on the page
		boolean isOutOfStock = driver.findElements(By.xpath("//div[contains(text(),'Out of Stock')]")).size() > 0;

        if (isOutOfStock) {
        	// If the item is unavailable, log it and return false to the calling method
            System.out.println("Product is Out of Stock. Skipping...");
            return false; 
        }
        
        try {
        	// Attempt to find and click the 'Add to Bag' button once it is clickable
            WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(obj.getAddButton())));
            addBtn.click();

            // Locate the 'Go to Bag' button and use JavaScript to click it
            // Using JavaScript ensures the click works even if the button is slightly obscured
            WebElement cartBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(obj.getGoButton())));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cartBtn);
            
         // Return true if the item was successfully added and we navigated to the cart
            return true;
            
        } catch (Exception e) {
        	// Log an error if the button is missing or cannot be clicked
            System.out.println("Add to Cart button not clickable or missing.");
            return false;
        }

	}

}
