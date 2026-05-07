package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	WebDriver driver;
	WebDriverWait wait;
	
	//Xpath to close the popup
	By closePopUpframe=By.xpath("//span[text()='✕']");
	//Xpath to verify the login page
	By loginVerifyText=By.xpath("//span[contains(text(),'Login')]");
	
	//constructor to call the driver
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		//set a timeout of 5 seconds
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	//To get current Url
	public String get_Current_Url() {
		String current_Url=driver.getCurrentUrl();
		return current_Url;
	}
	
	//Verify login page
	public boolean verify_LoginPage() {
		try {
			WebElement login_logo=wait.until(ExpectedConditions.visibilityOfElementLocated(loginVerifyText));
			return login_logo.isDisplayed();
			}
		catch(Exception e) {
			return false;
		}
	}
	
	//To close popup in Login page
	public void closePopup() {
		try {
		//wait until the clickable element is appeared
		wait.until(ExpectedConditions.elementToBeClickable(closePopUpframe)).click();
		}
		//Throws timeout exception catch it to handle
		catch(Exception e){
			System.out.println("Popup not present, continuing execution");
		}
	}
	
}
