package pages;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import configreader.ObjectReader;

public class JobDescriptionPage {
	WebDriver driver;
    WebDriverWait wait;
    ObjectReader objReader;
	public JobDescriptionPage(WebDriver driver) throws IOException{
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		this.objReader = new ObjectReader();
	}
    private By resultsHeader() {
        return By.cssSelector(objReader.getHeader());
    }
    private By resultsExperienceValue() {
        return By.cssSelector(objReader.getExperience());
    }
    public String getResultsHeaderText(String expectedText) {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(resultsHeader(), expectedText));
        return driver.findElement(resultsHeader()).getText();
    }
    public String getResultsExperienceValue(String expectedValue) {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(resultsExperienceValue(), expectedValue));
        return driver.findElement(resultsExperienceValue()).getText();
    }
}
