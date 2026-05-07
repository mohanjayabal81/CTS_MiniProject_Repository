package utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShotUtil{
    // Folder where screenshot will be saved
    private static final String SCREENSHOT_DIR = "Screenshots/";
    public static String capture(WebDriver driver, String testName) {
        String fileName  = testName + "_" + ".png";
        String filePath  = SCREENSHOT_DIR + fileName;
        try {
            // Create Screenshots/ folder if it doesn't exist
            File dir = new File(SCREENSHOT_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            // Take screenshot and save to file
            File src  = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(filePath);
            FileUtils.copyFile(src, dest);
            System.out.println("[SCREENSHOT] Saved: " + dest.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("[SCREENSHOT ERROR] Could not save screenshot: " + e.getMessage());
        }
        return filePath;
    }
}