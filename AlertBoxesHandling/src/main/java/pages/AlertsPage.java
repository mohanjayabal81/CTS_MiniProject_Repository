package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import configreader.ObjectReader;


//Page Object for the Alerts demo page
public class AlertsPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private ObjectReader config;

    // Initializes page dependencies with given driver 
    public AlertsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.config = new ObjectReader();         
    }
    

    // --- Navigation ---
    //Hovers over SwitchTo menu and clicks Alerts option
    public void navigateToAlertsSection() {
        Actions action = new Actions(driver);
        WebElement menu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(config.getSwitchToMenu())));
        // hover to reveal dropdown
        action.moveToElement(menu).perform();
        // click Alerts from dropdown
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAlertsOption()))).click();
    }
    

    // --- Simple Alert ---
    //Clicks the simple alert tab and triggers the alert
    public void clickAlertButton() {
    	// switch to simple alert tab
        driver.findElement(By.linkText(config.getAlertTab())).click();
        // trigger the alert
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getAlertBtn()))).click();
    }
    

    // --- Confirm Box ---
    //Clicks the confirm alert tab and triggers the confirm box
    public void openConfirmBox() {
    	// switch to confirm alert tab
        driver.findElement(By.linkText(config.getConfirmTab())).click();
        // trigger the confirm box
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getConfirmBtn()))).click();
    }


    //Returns result message shown after confirm interaction
    public String getConfirmResultMessage() {
    	// reads result text
        return driver.findElement(By.id(config.getConfirmMsg())).getText();
    }

    // --- Prompt Box ---
    //Clicks the prompt tab and triggers the prompt box
    public void openPromptBox() {
    	// switch to prompt alert tab
        driver.findElement(By.linkText(config.getPromptTab())).click();
        // trigger the prompt box
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(config.getPromptBtn()))).click();
    }
    

    //Returns result message shown after prompt interaction
    public String getPromptResultMessage() {
    	// reads result text
        return driver.findElement(By.id(config.getResultMsg())).getText();
    }
}