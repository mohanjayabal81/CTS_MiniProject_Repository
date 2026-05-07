package utilities;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

// Utility class to capture and save screenshots during test execution.

public class ScreenshotUtil {

    
//     Captures a screenshot and saves it to the screenshots folder.
//     Throws an error if the screenshots folder does not exist,
//     so the user is clearly informed to create it before running tests.
     
    public static void takeScreenshot(WebDriver driver, String fileName) throws IOException {

        File screenshotDir = new File("screenshots/");

        // Inform user clearly if folder is missing rather than creating it silently
        if (!screenshotDir.exists()) {
            throw new IllegalStateException(
                "Screenshots folder not found at: " + screenshotDir.getAbsolutePath()
                + " - Please create the 'screenshots' folder in your project root before running tests."
            );
        }

        File src  = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest = new File("screenshots/" + fileName + ".png");
        FileHandler.copy(src, dest);

        System.out.println("Screenshot saved at: " + dest.getAbsolutePath());
    }

    
//     Captures a screenshot, saves it, and returns the file path.
//     Used by Extent Reports to attach screenshots to the report.
     
    public static String captureAndReturn(WebDriver driver, String fileName) throws IOException {

        File screenshotDir = new File("screenshots/");

        if (!screenshotDir.exists()) {
            throw new IllegalStateException(
                "Screenshots folder not found at: " + screenshotDir.getAbsolutePath()
                + " - Please create the 'screenshots' folder in your project root before running tests."
            );
        }

        File src  = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest = new File("screenshots/" + fileName + ".png");
        FileHandler.copy(src, dest);

        return dest.getAbsolutePath();
    }
}