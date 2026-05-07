package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {

	 private WebDriver driver;
	 public Login(WebDriver driver) {
		 this.driver = driver;
	 }
	 By submitButton = By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[2]");
	 By signUpButton =  By.id("login2");
	 By userName = By.id("loginusername");
	 By passKey = By.id("loginpassword");
	 public void clkBtn() {
		 driver.findElement(signUpButton).click();
	 }
	 
	 public void enterCredentials(String username,String password) {
		 driver.findElement(userName).sendKeys(username);
		 driver.findElement(passKey).sendKeys(password);
	 }
	 public void clicksup() {
		 driver.findElement(submitButton).click();
	 }
	 public String verifyLogin() {
		 return new WebDriverWait(driver, Duration.ofSeconds(10))
			        .until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")))
			        .getText();
//		 System.out.println(usrname);
//	     return usrname;
	 }
}
