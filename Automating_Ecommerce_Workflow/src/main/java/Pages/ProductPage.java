package Pages;
 
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
 
public class ProductPage {
 
    WebDriver driver;
 
    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }
 
    By addToCart = By.xpath("//a[text()='Add to cart']");
 
    public void addProductToCart() {
    	 //  Wait until button is clickable before clicking
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.elementToBeClickable(addToCart))
            .click();

        //  Wait for alert and accept
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }
}
 