package pages;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import configReader.ObjectReader;

// Handles home page popup and search actions
public class FlipkartHomePage {

    private WebDriver driver;
    private ObjectReader or;
    private WebDriverWait wait;

    // Constructor
    public FlipkartHomePage(WebDriver driver) throws IOException {
        this.driver = driver;
        or = new ObjectReader();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Close the login popup on the home page
    public void closeLoginPopup() {
        try {
            WebElement popupClose = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath(or.getPopupCloseButtonXpath()))
            );
            popupClose.click();
            System.out.println(" Login popup closed.");
        } catch (Exception e) {
            // If popup does not appear, just print and continue
            System.out.println("Popup not found: " + e.getMessage());
        }
    }

    // Type keyword in search box and click the auto-suggestion
    public void searchMobilesUnder15000() throws InterruptedException {
        try {
            WebElement searchBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(or.getSearchBoxXpath()))
            );
            searchBox.sendKeys("mobiles under 15000");
            Thread.sleep(2000);

            WebElement suggestion = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath(or.getMobilesUnder15000Xpath()))
            );
            suggestion.click();
            Thread.sleep(3000);
            System.out.println("[INFO] Search done.");
        } catch (Exception e) {
            // Print the error and stop the test
            System.out.println("Search failed: " + e.getMessage());
        }
    }
}


