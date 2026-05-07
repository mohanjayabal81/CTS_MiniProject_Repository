package Utils;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

// Utility class containing reusable helper methods for test automation
public class ModularMethods {

    // Captures a screenshot of the current browser window, saves it to /Screenshots/ folder, and returns the file path
    public String screenshot(WebDriver driver, String name) throws IOException {
        TakesScreenshot scn = (TakesScreenshot) driver;
        File src = scn.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/Screenshots/" + name + ".png";
        File dest = new File(path);
       // FileUtils.copyFile(src, dest);
        return path;
    }
}