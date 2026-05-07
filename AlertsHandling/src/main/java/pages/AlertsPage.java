package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import java.time.Duration;

public class AlertsPage {
    WebDriver driver;
    WebDriverWait wait;

    private By switchElementLink=By.linkText("SwitchTo");
    private By alertLink=By.linkText("Alerts");
    private By alertWithOkLink=By.linkText("Alert with OK");
    private By alertWithOkCancelLink=By.linkText("Alert with OK & Cancel");
    private By confirmAlertId=By.id("demo");
    private By promptBoxLink=By.linkText("Alert with Textbox");
    private By promptBoxMsgId=By.id("demo1");

    public AlertsPage(WebDriver driver) throws IOException {
        this.driver=driver;
        this.wait=new WebDriverWait(driver, Duration.ofSeconds(10));
    }

//    navigate to alerts page
    public void navigateToAlertsPage(){
        WebElement switchElement=driver.findElement(switchElementLink);
        Actions actions=new Actions(driver);
        actions.moveToElement(switchElement).perform();
        driver.findElement(alertLink).click();
    }

    //    Checking Alert is present or not
    public boolean isAlertPresent(){
        try{
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        }catch (TimeoutException e){
            System.out.println("No alert present"+e.getMessage());
            return false;
        }
    }

    //    Simple alert handling---
    public String handleSimpleAlert(String xPath){
        driver.findElement(alertWithOkLink).click();
        driver.findElement(By.xpath(xPath)).click();
        if(isAlertPresent()){
            Alert simpleAlert=driver.switchTo().alert();
            String simpleAlertMsg=simpleAlert.getText();
            simpleAlert.accept();
            return simpleAlertMsg;
        }
        return null;
    }

//    Confirmation alert handling---
    public String handleConfirmationAlert(String xPath){
        driver.findElement(alertWithOkCancelLink).click();
        driver.findElement(By.xpath(xPath)).click();
        if(isAlertPresent()){
            Alert confirmAlert=driver.switchTo().alert();
            confirmAlert.dismiss();
            String confirmAlertMsg=driver.findElement(confirmAlertId).getText();
            return confirmAlertMsg;
        }
        return null;
    }

//    Prompt Alert handling---
    public String handlePromptAlert(String name,String xPath){
        driver.findElement(promptBoxLink).click();
        driver.findElement(By.xpath(xPath)).click();
        if(isAlertPresent()){
            Alert promptAlert=driver.switchTo().alert();
            promptAlert.sendKeys(name);
            promptAlert.accept();
            String promptAlertMsg=driver.findElement(promptBoxMsgId).getText();
            return promptAlertMsg;
        }
        return null;
    }
}