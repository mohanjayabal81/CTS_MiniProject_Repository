package utils;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

/*
 * Utility class for capturing screenshots
 */
public class ScreenshotUtils {

    public static String captureScreenshot(WebDriver driver, String name) {

        String folder = System.getProperty("user.dir")
                + File.separator + "Screenshots";

        String path = folder + File.separator + name + ".png";

        try {

            File dir = new File(folder);
            if (!dir.exists())
                dir.mkdirs();

            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);
            File dest = new File(path);

            FileUtils.copyFile(src, dest);

            return path;

        } catch (Exception e) {
            return null;
        }
    }
}