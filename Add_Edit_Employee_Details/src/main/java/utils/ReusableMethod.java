package utils;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReusableMethod {

    WebDriver driver;
    WebDriverWait wait;
    objectFetch u;

    //  Constructor
    public ReusableMethod(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            this.u = new objectFetch();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //  Reusable Submit Button Method + Screenshot
    public void sub_Button(String pageName) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath(u.getLocator("subbutton"))
            )).click();

            //  Screenshot after submit
            ScreenshotUtil.takeScreenshot(driver, pageName);

        } catch (Exception e) {
            System.out.println("Submit button click failed: " + e.getMessage());
        }
    }

    //  IMPORTANT: Wait for OrangeHRM loader to disappear
    public void waitForLoaderToDisappear() {
        try {
            WebDriverWait loaderWait = new WebDriverWait(driver, Duration.ofSeconds(20));
            loaderWait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.xpath(u.getLocator("loader"))
            ));
        } catch (Exception e) {
            // Loader already disappeared or not present
            System.out.println("Loader not visible, proceeding...");
        }
    }
}
