package TestingPages;

import java.io.IOException;
//import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ReusableMethod;
import utils.objectFetch;

import org.testng.asserts.SoftAssert;

public class login {

   private  WebDriver driver;
    private objectFetch object;
    ReusableMethod rm;
    SoftAssert softAssert;

    public login(WebDriver driver) {
        this.driver = driver;
        this.rm = new ReusableMethod(driver);
        this.softAssert = new SoftAssert();
        try {
            this.object = new objectFetch();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loginPage(WebDriver driver) {

        driver.get(object.getUrl());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(object.getLocator("username")))).sendKeys("Admin");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(object.getLocator("password")))).sendKeys("admin123");

        rm.sub_Button("LoginPage");

        //  Soft Assertions
        softAssert.assertTrue(
                driver.getCurrentUrl().contains("dashboard"),
                "Login failed - Dashboard URL not found"
        );

        softAssert.assertAll(); //  VERY IMPORTANT
    }
}