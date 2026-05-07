package Pages;
 
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
 
public class CheckoutPage {
    //per page repository
    private WebDriver driver;
    By name = By.id("name");
    By country = By.id("country");
    By city = By.id("city");
    By card = By.id("card");
    By month = By.id("month");
    By year = By.id("year");
    By purchaseBtn = By.xpath("//button[text()='Purchase']");
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }
 
   
 
    public void enterDetails(String n, String c, String ci, String cd, String m, String y) {
    	new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.visibilityOfElementLocated(name))
        .sendKeys(n);
    	//driver.findElement(name).sendKeys(n);
        driver.findElement(country).sendKeys(c);
        driver.findElement(city).sendKeys(ci);
        driver.findElement(card).sendKeys(cd);
        driver.findElement(month).sendKeys(m);
        driver.findElement(year).sendKeys(y);
    }
  
    public String takeScreenshot() throws IOException {
    	String path = System.getProperty("user.dir") + "/screenshots/checkout_" + System.currentTimeMillis() + ".png";
    	   
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File(path));
        return path;
    }
    public void clickPurchase() {
        driver.findElement(purchaseBtn).click();
    }
}
 