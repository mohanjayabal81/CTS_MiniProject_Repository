package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {
	
	WebDriver driver;
	public ForgotPasswordPage(WebDriver driver) {
		this.driver=driver;
	}
	
	//Click next and capture alert
	public String clickNextAndGetAlert() {
		driver.findElement(By.name("next")).click();
		
		Alert alert=driver.switchTo().alert();
		String text=alert.getText();
		alert.accept();
		driver.navigate().back();
		return text;
	}

}
