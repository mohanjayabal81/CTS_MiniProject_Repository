package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPage {
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		this.driver = driver;
	}
	
	//Get Landing Page Title
	public String verifyLandingPage() {
		String landingTitle=driver.getTitle();
		return landingTitle;
	}
	
	//Click Sign In Link
	public void clickSignIn() {
		driver.findElement(By.linkText("Sign in")).click();
	}
	
	
}
