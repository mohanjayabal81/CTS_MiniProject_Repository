package Pages;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ConfigReader.Object_Reader;

public class CreateAccountPage {
	WebDriver driver;
    WebDriverWait wait;
    Object_Reader objReader;
    String parentHandle;
    Set<String> handles;
	public CreateAccountPage(WebDriver driver)throws IOException{
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		this.objReader = new Object_Reader();
	}
	private By headerLinkText() {
		return By.xpath(objReader.getHeader());
	}
	private By allLinksTagName() {
		return By.tagName(objReader.getLinksTagName());
	}
	private By termsAndCondtionsLink() {
		return By.linkText(objReader.getTermsAndCondtionsLink());
	}
	public String verifyHeader(String acHeader){
		
		wait.until(ExpectedConditions.textToBePresentInElementLocated(headerLinkText(),acHeader));
		return driver.findElement(headerLinkText()).getText();
	}
	public List<WebElement> getAllLinks(){
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(allLinksTagName()));
	}
	public void clickOnTermsAndConditions() {
		WebElement el = wait.until(ExpectedConditions.elementToBeClickable(termsAndCondtionsLink()));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", el);
	}
	public void switchToChild() {
	    String parentHandle = driver.getWindowHandle();
	    Set<String> handles = driver.getWindowHandles();
	    for (String handle : handles) {
	        if (!handle.equals(parentHandle)) {
	            driver.switchTo().window(handle);
	            break;
	        }
	    }
	}
}
