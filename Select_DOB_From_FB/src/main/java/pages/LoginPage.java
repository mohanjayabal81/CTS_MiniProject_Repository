package pages;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ObjectReader;

public class LoginPage {
    private WebDriver driver;
    WebDriverWait wait;
    public ObjectReader ob; // Made public to access in the test class if needed
    public LoginPage(WebDriver driver) throws IOException {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.ob = new ObjectReader();
    }
    public void openCreateAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Create new account"))).click();
    }
    public void setUserName() {
        WebElement username = driver.findElement(By.id("_R_1cl2p4jikacppb6amH1_"));
        username.click();
        username.sendKeys(ob.getName()); // Fetched from properties
    }
    public void setSurname() {
        WebElement surname = driver.findElement(By.id("_R_1kl2p4jikacppb6amH1_"));
        surname.click();
        surname.sendKeys(ob.getSurname()); // Fetched from properties
    }
    public void enterDay() {
        String day = ob.getDay(); // Fetched from properties
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='Select day']"))).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        List<WebElement> options = driver.findElements(By.xpath("//div[@role='option']"));
        for (WebElement option : options) {
            if (option.getText().trim().equals(day)) {
                js.executeScript("arguments[0].scrollIntoView(true);", option);
                js.executeScript("arguments[0].click();", option);
                break;
            }
        }
    }
    public void enterMonth(){
        String month = ob.getMonth(); // Fetched from properties
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='Select month']"))).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        List<WebElement> options = driver.findElements(By.xpath("//div[@role='option']"));
        for (WebElement option : options) {
            if (option.getText().trim().equals(month)) {
                js.executeScript("arguments[0].scrollIntoView(true);", option);
                js.executeScript("arguments[0].click();", option);
                break;
            }
        }
    }
    public void enterYear(){
        String year = ob.getYear(); // Fetched from properties
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='Select year']"))).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        List<WebElement> options = driver.findElements(By.xpath("//div[@role='option']"));
        for (WebElement option : options) {
            if (option.getText().trim().equals(year)) {
                js.executeScript("arguments[0].scrollIntoView(true);", option);
                js.executeScript("arguments[0].click();", option);
                break;
            }
        }
    }
    public void selectGender() {
        String gender = ob.getGender(); // Fetched from properties
        WebElement selection = driver.findElement(By.xpath("//span[normalize-space()='Select your gender']/ancestor::div[1]"));
        selection.click();
        WebElement genderselec = driver.findElement(By.xpath("//div[contains(text(),'" + gender + "')]"));
        genderselec.click();   
    }  
    public void enterMobileNumber() {
        WebElement number = driver.findElement(By.id("_R_6ad8p4jikacppb6amH1_"));
        number.sendKeys(ob.getMobileNumber()); // Fetched from properties
    }

    public void enterPassword() {
        WebElement passField = driver.findElement(By.id("_R_clap4jikacppb6amH1_"));
        passField.click();
    }

    public void submit() throws InterruptedException {
        WebElement sum = driver.findElement(By.xpath("//span[normalize-space()='Submit']"));
        sum.click();
        Thread.sleep(2000);
    }
}