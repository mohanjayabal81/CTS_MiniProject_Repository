package Pages;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import ConfigReader.Object_Reader;

public class TermsAndCondtionsPage {
	WebDriver driver;
    WebDriverWait wait;
    Object_Reader objReader;
    String parentHandle;
    public TermsAndCondtionsPage(WebDriver driver) throws IOException {
    	this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		this.objReader = new Object_Reader();
		this.parentHandle = driver.getWindowHandle();
    }

	public void switchToParent() {
		driver.switchTo().window(parentHandle);
	}
	public String getTileOfTnC(){
		String acTitle = driver.getTitle();
		return acTitle;
	}
}
