package Pages;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ConfigReader.Object_Reader;
public class LandingPage {
    WebDriver driver;
    WebDriverWait wait;
    Object_Reader objReader;
	public LandingPage(WebDriver driver)throws IOException{
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		this.objReader = new Object_Reader();
	}
	private By createAccountLinkText() {
		return By.linkText(objReader.getCreateAccountLink());
	}
	public void clickOnCreateAccount() {
		wait.until(ExpectedConditions.elementToBeClickable(createAccountLinkText())).click();
	}
}
