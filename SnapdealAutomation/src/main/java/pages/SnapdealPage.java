package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SnapdealPage {

    // WebDriver reference to control browser
    WebDriver driver;
    WebDriverWait wait;

    // Constructor to initialize WebDriver
    public SnapdealPage(WebDriver driver) {
        this.driver = driver;
    }

    // Page locators
    By searchBox = By.id("search-box-input");
    By lowPrice = By.name("fromVal");
    By highPrice = By.name("toVal");
    By goButton = By.xpath("//*[@id='content_wrapper']/div[7]/div[3]/div/div[1]/div[2]/div[2]/div[3]/div/div[5]");
    By sortDropdown = By.className("sort-selected");
    By sortLowToHigh = By.xpath("//*[@id='content_wrapper']/div[7]/div[4]/div[3]/div[1]/div/div[2]/ul/li[2]");
    By productNames = By.cssSelector("p.product-title");
    By productPrices = By.cssSelector("span.product-price");

    // Search for a product
    public void searchProduct(String productName) {
        try {
            driver.findElement(searchBox).sendKeys(productName, Keys.ENTER);
        } catch (NoSuchElementException e) {
            System.out.println("Search box not found");
        } catch (Exception e) {
            System.out.println("Error while searching product: " + e.getMessage());
        }
    }

    // Apply price range filter
    public void applyPriceFilter(String min, String max) {
        try {
            driver.findElement(lowPrice).clear();
            driver.findElement(lowPrice).sendKeys(min);

            driver.findElement(highPrice).clear();
            driver.findElement(highPrice).sendKeys(max);

            driver.findElement(goButton).click();
        } catch (NoSuchElementException e) {
            System.out.println("Price filter fields not found");
        } catch (Exception e) {
            System.out.println("Error applying price filter: " + e.getMessage());
        }
    }

    // Check if Snapdeal home page is opened
    public boolean isLandingPageDisplayed() {
        try {
            return driver.getCurrentUrl().contains("snapdeal.com");
        } catch (Exception e) {
            return false;
        }
    }

    // Check if search results page is shown
    public boolean isSearchResultDisplayed() {
        try {
            return driver.getCurrentUrl().contains("search");
        } catch (Exception e) {
            return false;
        }
    }

    // Sort products from low price to high price
    public void sortByLowToHigh() {
        try {
            driver.findElement(sortDropdown).click();
            driver.findElement(sortLowToHigh).click();
        } catch (NoSuchElementException e) {
            System.out.println("Sorting option not available");
        } catch (Exception e) {
            System.out.println("Error while sorting: " + e.getMessage());
        }
    }

    // Print top 5 product names and prices
    public void printTop5Products() {
        try {
            List<WebElement> names = driver.findElements(productNames);
            List<WebElement> prices = driver.findElements(productPrices);

            int count = Math.min(5, Math.min(names.size(), prices.size()));

            if (count == 0) {
               System.out.println("No products found");
                return;
            }

            for (int i = 0; i < count; i++) {
                System.out.println("Name  :- " + names.get(i).getText());
                System.out.println("Price :- " + prices.get(i).getText());
                System.out.println("--------------------------------");
            }

        } catch (Exception e) {
            System.out.println("Error while fetching products: " + e.getMessage());
        }
    }
}