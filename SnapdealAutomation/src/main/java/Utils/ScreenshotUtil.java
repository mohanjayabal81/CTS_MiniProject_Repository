package Utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    // Method to take screenshot
    public static void takeScreenshot(WebDriver driver, String testName) {

        // Create timestamp to avoid duplicate screenshot names
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(new Date());

        // Build screenshot file path
        String path = System.getProperty("user.dir")
                + "/screenshots/"
                + testName + "_" + timestamp + ".png";

        try {
            // Take screenshot from browser
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);

            // Set destination path
            File dest = new File(path);

            // Save screenshot to folder
            FileUtils.copyFile(src, dest);
            System.out.println("Screenshot saved: " + path);

        } catch (IOException e) {
            // Print error if screenshot fails
            System.out.println("Screenshot capture failed: " + e.getMessage());
        }
    }
}