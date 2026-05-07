package pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.DriverManager;

public class LandingPage {
    
    // WebDriver instance for interacting with landing page elements
    private WebDriver driver;
    
    // Reference to SearchFilter page/component (used later if needed)
    SearchFilter search;

    // Constructor initializes WebDriver for this page
    public LandingPage(WebDriver driver) throws IOException {
        this.driver = driver;
    }

    // Clicks on the Advanced Search button from landing page
    public void advancesBtn() {
        try {
            // Locate advanced search button using class name
            WebElement adBtn = driver.findElement(
                By.className("gh-search-button__advanced-link")
            );
            
            // Perform click action
            adBtn.click();
        } catch (Exception e) {
            // Handles scenario when element is not found or not clickable
            System.out.println("1st");
        }
    }
}