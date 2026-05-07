package pages;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

// Fetches the top 5 mobiles and validates the first mobile price
public class FlipkartMobileFetching {

    private WebDriver driver;

    // Mobile name locators  per page object Repository
    By firstMobileName  = By.xpath("//div[@class='ZFwe0M row'][1]//div[@class='RG5Slk']");
    By secondMobileName = By.xpath("(//div[@class='ZFwe0M row'])[2]//div[@class='RG5Slk']");
    By thirdMobileName  = By.xpath("(//div[@class='ZFwe0M row'])[3]//div[@class='RG5Slk']");
    By fourthMobileName = By.xpath("(//div[@class='ZFwe0M row'])[4]//div[@class='RG5Slk']");
    By fifthMobileName  = By.xpath("(//div[@class='ZFwe0M row'])[5]//div[@class='RG5Slk']");

    // Mobile price locators
    By firstMobilePrice  = By.xpath("//div[@class='ZFwe0M row'][1]//div[@class='hZ3P6w DeU9vF']");
    By secondMobilePrice = By.xpath("(//div[@class='ZFwe0M row'])[2]//div[@class='hZ3P6w DeU9vF']");
    By thirdMobilePrice  = By.xpath("(//div[@class='ZFwe0M row'])[3]//div[@class='hZ3P6w DeU9vF']");
    By fourthMobilePrice = By.xpath("(//div[@class='ZFwe0M row'])[4]//div[@class='hZ3P6w DeU9vF']");
    By fifthMobilePrice  = By.xpath("(//div[@class='ZFwe0M row'])[5]//div[@class='hZ3P6w DeU9vF']");

    // Constructor
    public FlipkartMobileFetching(WebDriver driver) {
        this.driver = driver;
    }

    // Check first mobile price is less than Rs.30000
    public void assertFirstMobilePriceBelowThreshold(String priceText) {
        try {
            // Remove Rs symbol and commas, then parse to int
            int price = Integer.parseInt(priceText.replaceAll("[^0-9]", ""));
            Assert.assertTrue(price < 30000, "First mobile price is not below Rs.30000!");
            System.out.println("[PASS] Price Rs." + price + " is below Rs.30000");
        } catch (Exception e) {
            System.out.println("[FAIL] Price assertion failed: " + e.getMessage());
        }
    }

    // Fetch name and price of top 5 mobiles, returns as name->price map
    public Map<String, String> fetchTop5Mobiles() {
        Map<String, String> mobileList = new LinkedHashMap<>();
        try {
            // Mobile 1 — also runs the price checkpoint
            String name1  = driver.findElement(firstMobileName).getText();
            String price1 = driver.findElement(firstMobilePrice).getText();
            assertFirstMobilePriceBelowThreshold(price1);
            mobileList.put(name1, price1);

            // Mobile 2
            String name2  = driver.findElement(secondMobileName).getText();
            String price2 = driver.findElement(secondMobilePrice).getText();
            mobileList.put(name2, price2);

            // Mobile 3
            String name3  = driver.findElement(thirdMobileName).getText();
            String price3 = driver.findElement(thirdMobilePrice).getText();
            mobileList.put(name3, price3);

            // Mobile 4
            String name4  = driver.findElement(fourthMobileName).getText();
            String price4 = driver.findElement(fourthMobilePrice).getText();
            mobileList.put(name4, price4);

            // Mobile 5
            String name5  = driver.findElement(fifthMobileName).getText();
            String price5 = driver.findElement(fifthMobilePrice).getText();
            mobileList.put(name5, price5);

        } catch (Exception e) {
            System.out.println("[FAIL] fetchTop5Mobiles failed: " + e.getMessage());
        }
        return mobileList;
    }
}