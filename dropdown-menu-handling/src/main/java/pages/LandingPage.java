package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.ElementUtils;

public class LandingPage {
	
	WebDriver driver;
	ElementUtils elementUtils;
	
	public LandingPage(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
	}
	
	// Page elements
	private By rediffLogo = By.xpath("//img[@alt='Rediff.com logo']");
	private By signInText = By.xpath("//h2[normalize-space()='Sign in']");
	
	// Get current page URL
	public String getPageUrl() {
		return driver.getCurrentUrl();
	}
	
	// Get current page title
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	// Check for broken UI elements or blank screens
	public boolean isPageLoaded() {
		return elementUtils.isElementDisplayed(rediffLogo) && elementUtils.isElementDisplayed(signInText);
	}
	
	// Check if Rediff logo is displayed
	public boolean isLogoDisplayed() {
		return elementUtils.isElementDisplayed(rediffLogo);
	}
	
	// Check if Sign In text is displayed
	public boolean isSignInTextDisplayed() {
        return elementUtils.isElementDisplayed(signInText);
    }
}
