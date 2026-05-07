package utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


//Utility class for handling browser alert interactions
public class AlertHandlers {

    // wait instance scoped to this handler
    private WebDriverWait wait;
    // driver stored so screenshot method can use it
    private WebDriver driver;

    //Initializes wait with given driver and 5s timeout
    public AlertHandlers(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Captures screenshot on failure attaches to Extent report
    public void captureScreenshot(ExtentTest test, String testName) {
        try {
            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            String fileName = testName.replaceAll("\\s+", "") + "_" + timestamp + ".png";
            String path = System.getProperty("user.dir") + "/ScreenShots/" + fileName;

            
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);


            File dest = new File(path);
//            dest.getParentFile().mkdirs();
            FileUtils.copyFile(src, dest);

            // attaches screenshot into the Extent report node
            test.addScreenCaptureFromPath(path, fileName);

            Reporter.log("Screenshot saved: " + path, true);
        } catch (IOException e) {
            Reporter.log("Screenshot capture failed: " + e.getMessage(), true);
        }
    }

    //Waits until alert is present and returns it
    private Alert waitForAlert() {
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    //Accepts (clicks OK on) the alert
    public void acceptAlert() {
        waitForAlert().accept();
    }

    //Dismisses (clicks Cancel on) the alert
    public void dismissAlert() {
        waitForAlert().dismiss();
    }

    //Types given text into prompt alert and accepts it
    public void typeAndAccept(String text) {
        Alert alert = waitForAlert();
        alert.sendKeys(text);
        alert.accept();
    }

    //Returns the text message displayed on the alert
    public String getAlertText() {
        return waitForAlert().getText();
    }
}