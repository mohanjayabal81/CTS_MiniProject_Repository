package Pages;

import java.time.Duration;
import java.time.LocalDate;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// Page Object class for Trivago landing page
public class LandingPage {

    private WebDriverWait wait;
    private WebDriver driver;

    // Locators
    private By destinationInput = By.cssSelector("#input-auto-complete");
    private By datepickerButton = By.xpath("//button[@data-testid='search-form-calendar']");
    private By plusBtn = By.xpath("//button[@data-testid='adults-amount-plus-button']");
    private By minusBtn = By.xpath("//button[@data-testid='adults-amount-minus-button']");
    private By applyBtn = By.xpath("//button[@data-testid='guest-selector-apply']");
    private By sortBtn = By.xpath("//*[@id='refinement-row-filters']/li[2]/div/button");
    private By sortByRatingBtn = By.xpath("//input[@data-testid='sorting-index-3']");
    private By filterBtn = By.xpath("//*[@id='refinement-row-filters']/li[2]/div/div/div/div/section/footer/button");

    // Initialize driver and set 10-second explicit wait
    public LandingPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Types destination and presses Enter to search
    public void destination(String dest) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(destinationInput));
        input.click();
        input.clear();
        input.sendKeys(dest);
        input.sendKeys(Keys.ENTER);
    }

    // Opens the date picker calendar
    public void datePickerClick() {
        wait.until(ExpectedConditions.elementToBeClickable(datepickerButton)).click();
    }

    // Selects a date from the calendar, retries on StaleElementReferenceException
    public void selectDate(LocalDate date) {
        By dateLocator = By.xpath("//button[@data-testid='valid-calendar-day-" + date.toString() + "']");

        wait.until(d -> {
            try {
                WebElement dateElement = d.findElement(dateLocator);
                dateElement.click();
                return true;
            } catch (StaleElementReferenceException e) {
                return false; // returning false causes wait.until to retry
            }
        });
    }        
    

    // Increases adult guest count
    public void plusButton() {
        wait.until(ExpectedConditions.elementToBeClickable(plusBtn)).click();
    }

    // Decreases adult guest count
    public void minusButton() {
        wait.until(ExpectedConditions.elementToBeClickable(minusBtn)).click();
    }

    // Confirms guest selection
    public void applyButton() {
        wait.until(ExpectedConditions.elementToBeClickable(applyBtn)).click();
    }

    // Opens the sort dropdown
    public void sortButton() {
        wait.until(ExpectedConditions.elementToBeClickable(sortBtn)).click();
    }

    // Selects "Sort by Rating" option
    public void sortByRatingButton() {
        wait.until(ExpectedConditions.elementToBeClickable(sortByRatingBtn)).click();
    }

    // Applies the selected sort filter
    public void filterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(filterBtn)).click();
    }

    // Returns the rating text of a hotel at the given index (1-based)
    public String getRating(int index) {
        By ratingLocator = By.xpath("(//span[@data-testid='aggregate-rating'])[" + index + "]/span/span");
        WebElement ratingElement = wait.until(ExpectedConditions.visibilityOfElementLocated(ratingLocator));
        return ratingElement.getText();
    }
}