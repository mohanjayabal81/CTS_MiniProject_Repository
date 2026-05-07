package pages;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import configReader.ObjectReader;

// Handles result verification, filters and sort on the results page
public class FlipkartResultsPage {

    private WebDriver driver;
    private ObjectReader or;
    private JavascriptExecutor js;

    // By locator declared directly in the class
    By newestFirst = By.xpath("//div[@class='lvJbLV']//div[@class='s6YJlm']//div[text()='Newest First']");

    // Constructor
    public FlipkartResultsPage(WebDriver driver) throws IOException {
        this.driver = driver;
        or = new ObjectReader();
        js = (JavascriptExecutor) driver;
    }

    // Verify the results page shows "mobiles under 15000" label
    public void verifySearchResultCriteria() {
        try {
            WebElement resultLabel = driver.findElement(By.xpath(or.getSearchResultVerifyXpath()));
            Assert.assertTrue(resultLabel.getText().contains("mobiles under 15000"), // hard assertion
                    "Search result label mismatch!");
        } catch (Exception e) {
            System.out.println("FAIL verifySearchResultCriteria failed: " + e.getMessage());
        }
    }

    // Click the filters button to open the filter panel
    public void openFiltersPanel() {
        try {
            driver.findElement(By.xpath(or.getDisplayFiltersXpath())).click();
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("FAIL openFiltersPanel failed: " + e.getMessage());
        }
    }

    // Scroll to price section and set max price to Rs.10000
    public void setMaxPriceTo10000() {
        try {
            WebElement priceSection = driver.findElement(By.xpath(or.getPriceRangeSectionXpath()));
            //argument means the price section
            js.executeScript("arguments[0].scrollIntoView(true);", priceSection);
            Thread.sleep(1000);

            WebElement maxPriceDropdown = driver.findElement(By.xpath(or.getMaxPriceDropdownXpath()));
            maxPriceDropdown.click();

            // Select Rs.10000 from the dropdown
            Select select = new Select(maxPriceDropdown);
            select.selectByVisibleText("₹10000");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("[FAIL] setMaxPriceTo10000 failed: " + e.getMessage());
        }
    }

    // Select Pie OS version from the filter panel
    public void selectPieOsVersionFilter() {
        try {
            // Scroll to OS section
            WebElement osSection = driver.findElement(By.xpath(or.getOperatingSystemSectionXpath()));
            js.executeScript("arguments[0].scrollIntoView(true);", osSection);
            Thread.sleep(2000);

            // Click OS Version Name heading to expand it
            driver.findElement(By.xpath(or.getOsVersionHeadingXpath())).click();
            Thread.sleep(2000);

            // Click MORE to see all versions
            driver.findElement(By.xpath(or.getShowMoreOsVersionsXpath())).click();
            Thread.sleep(2000);

            // Scroll to Pie option
            WebElement pieRef = driver.findElement(By.xpath(or.getScrollToPieReferenceXpath()));
            js.executeScript("arguments[0].scrollIntoView(true);", pieRef);
            Thread.sleep(2000);

            // Select Pie
            driver.findElement(By.xpath(or.getPieVersionXpath())).click();
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("FAIL selectPieOsVersionFilter failed: " + e.getMessage());
        }
    }

    // Click Newest First using By locator declared at class level
    public void sortByNewestFirst() {
        try {
            driver.findElement(newestFirst).click();
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("[FAIL] sortByNewestFirst failed: " + e.getMessage());
        }
    }
}