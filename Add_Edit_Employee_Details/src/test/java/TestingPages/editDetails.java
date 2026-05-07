package TestingPages;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import utils.ReusableMethod;
import utils.objectFetch;

public class editDetails {

   private  objectFetch object;
   private  WebDriver driver;
    WebDriverWait wait;
    ReusableMethod rm;
    SoftAssert softAssert;

    public editDetails(WebDriver driver) {
        this.driver = driver;
        try {
            this.object = new objectFetch();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.rm = new ReusableMethod(driver);
        this.softAssert = new SoftAssert();
    }

    public void Edit(WebDriver driver) throws Exception {

        try {
//        	Thread.sleep(3000);
        	rm.waitForLoaderToDisappear();
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath(object.getLocator("nationality")))).click();

            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath(object.getLocator("indian")))).click();

            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath(object.getLocator("marital")))).click();

            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath(object.getLocator("single")))).click();

            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath(object.getLocator("male")))).click();

            //  Submit + Screenshot
            rm.sub_Button("EditEmployeePage");

            //  Soft Assertions
            softAssert.assertTrue(
                    driver.getPageSource().contains("Single"),
                    "Marital Status update failed"
            );

            softAssert.assertTrue(
                    driver.getPageSource().contains("Indian"),
                    "Nationality update failed"
            );

        } catch (Exception e) {
            System.out.println("Editing Details Failed: " + e.getMessage());
            throw e;
        } finally {
           
            softAssert.assertAll();
        }
    }
}
