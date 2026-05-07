package Pages;
 
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
 
public class HomePage {
 
   private WebDriver driver;
 
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
 
    By firstProduct = By.xpath("(//a[@class='hrefch'])[1]");
 //
    public void selectFirstProduct() {
    	 new FluentWait<>(driver)
         .withTimeout(Duration.ofSeconds(20))
         .pollingEvery(Duration.ofSeconds(2))
         .ignoring(StaleElementReferenceException.class)
         .until(d -> {
             
             d.findElement(firstProduct).click();
             return true;
         });
    	//new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(firstProduct)).click();
        //driver.findElement(firstProduct).click();
    }
}
 