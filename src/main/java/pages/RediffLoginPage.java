package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class RediffLoginPage {

	WebDriver driver;

	public RediffLoginPage(WebDriver driver) {
		this.driver = driver;
	}

	//Click login and capture alert
	public String clickLoginAndGetAlert() {
		driver.findElement(By.name("proceed")).click();

		Alert alert = driver.switchTo().alert();
		String text = alert.getText();
		alert.accept();

		return text;
	}
	
	//Click forgot password
	public void clickForgotPassword() {
		driver.findElement(By.linkText("Forgot password?")).click();
	}

}
