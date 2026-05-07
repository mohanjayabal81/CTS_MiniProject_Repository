package TestingPages;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import utils.ReusableMethod;
import utils.objectFetch;

public class addDetails {

    public static final String first_Name = "Aravind";
    public static final String middle_Name = "Chowdary";
    public static final String last_Name = "Lingutla";
    public static final String New_emp_user =
            "Aravind" + (System.currentTimeMillis() % 10000);
    public static final String emp_Id =
            "2478661" + (System.currentTimeMillis() % 1000);

    private WebDriver driver;
   private objectFetch object;
    ReusableMethod rm;
    WebDriverWait wait;
    SoftAssert softAssert;

    public addDetails(WebDriver driver) {
        this.driver = driver;

        try {
            this.object = new objectFetch();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.rm = new ReusableMethod(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.softAssert = new SoftAssert();
    }

    public void add(WebDriver driver) throws InterruptedException {

        try {
            // Navigate to PIM
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath(object.getLocator("pim")))).click();

            // Click Add Employee
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath(object.getLocator("addEmployee")))).click();

            // Enter employee names
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(object.getLocator("firstName")))).sendKeys(first_Name);

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(object.getLocator("middleName")))).sendKeys(middle_Name);

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(object.getLocator("lastName")))).sendKeys(last_Name);

            // Clear and enter Employee ID
            WebElement empIdField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath(object.getLocator("empid"))));

            empIdField.sendKeys(Keys.CONTROL + "a");
            empIdField.sendKeys(Keys.BACK_SPACE);
            empIdField.sendKeys(emp_Id);

           
            rm.waitForLoaderToDisappear();

            // Click Create Login Details toggle
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath(object.getLocator("toggle")))).click();

            // Enter login credentials
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(object.getLocator("userName")))).sendKeys(New_emp_user);

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(object.getLocator("passwordField")))).sendKeys("Aravind@8082");

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(object.getLocator("confirmPassword")))).sendKeys("Aravind@8082");

            //  Submit + Screenshot
            rm.sub_Button("AddEmployeePage");

//              Soft Assertion – verify Personal Details page
            softAssert.assertTrue(
                    wait.until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath(object.getLocator("personalDetailsHeader"))
                    )).isDisplayed(),
                    "Employee was NOT added successfully"
            );

        } catch (Exception e) {
            System.out.println("Add Details Failed: " + e.getMessage());
            throw e;
        } finally {
            //  Mandatory for SoftAssert
            softAssert.assertAll();
        }
    }
}