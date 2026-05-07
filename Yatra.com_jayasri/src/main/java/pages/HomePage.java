
package pages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import utils.ObjectReader;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;
    private ObjectReader u;

    // Constructor initializes driver, wait and property reader
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.u = new ObjectReader();  //instance
    }

    
     // Handles popup if it appears
     
    public void handlePopup() {
        try {
            By popup = By.xpath(u.getLocator("popupCloseX"));
            wait.until(ExpectedConditions.elementToBeClickable(popup)).click();
        } catch (Exception e) {
            // Popup not present — continue execution
        }
    }

    
    //Clicks on Offers section using JavaScript
     
    public void clickOffers() {
        By offers = By.xpath(u.getLocator("offersLink"));
        WebElement element = wait.until(
                ExpectedConditions.presenceOfElementLocated(offers));
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);
    }
}