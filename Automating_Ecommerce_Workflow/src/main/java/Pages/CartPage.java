package Pages;
 
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
 
public class CartPage {
 
    private WebDriver driver;
 
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }
 
    By cartButton = By.id("cartur");
    By placeOrderButton = By.xpath("//button[text()='Place Order']");
 
    public void openCart() {
        driver.findElement(cartButton).click();
 
        // WAIT FOR CART PAGE LOAD
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(placeOrderButton));
    }
 
    public void clickPlaceOrder() {
        driver.findElement(placeOrderButton).click();
    }
}
 