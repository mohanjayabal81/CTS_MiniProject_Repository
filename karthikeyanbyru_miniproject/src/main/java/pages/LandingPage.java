package pages;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import configreader.ObjectReader;
public class LandingPage {
    WebDriver driver;
    WebDriverWait wait;
    ObjectReader objReader;
    // ---- Constructor ----
    public LandingPage(WebDriver driver) throws IOException {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.objReader = new ObjectReader();
    }
    // ---- Locators(loaded from object.properties)----
    private By keywordInput(){
        return By.xpath(objReader.getskillsComboBox());
    }
    private By locationInput() {
        return By.xpath(objReader.getLocationComboBox());
    }
    private By experienceDropdownArrow() {
        return By.className(objReader.getExperienceDropDown());
    }
    private By experienceOption() {
        return By.xpath(objReader.selectExperience());
    }
    private By searchButton() {
        return By.className(objReader.clickOnSearch());
    }
    // ---- Page Actions ----
    public String getPageTitle(){
    		return driver.getTitle();
    }
    public void enterKeyword(String keyword) 
    {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(keywordInput()));
        el.clear();
        el.sendKeys(keyword);
    }
    public void enterLocation(String location) 
    {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locationInput()));
        el.clear();
        el.sendKeys(location);
    }
    public void selectExperience() 
    {
        wait.until(ExpectedConditions.elementToBeClickable(experienceDropdownArrow())).click();
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(experienceOption()));
        option.click();
    }
    public void clickSearch()
    {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton())).click();
    }
}