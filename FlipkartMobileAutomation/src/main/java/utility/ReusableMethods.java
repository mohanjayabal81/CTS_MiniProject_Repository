package utility;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

// Utility class for report and screenshot
public class ReusableMethods {

    // Report and test objects
    public static ExtentReports extent;
    public static ExtentTest test;

    // Setup the extent report
    public static void setupReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter(
            "C:\\Users\\2478821\\eclipse-workspace\\FlipkartMobileAutomation\\Report\\ExtentReport.html"
        );
        extent = new ExtentReports();
        extent.attachReporter(spark);
        test = extent.createTest("Flipkart Mobile Search and Price Validation");
    }

    // Take screenshot and return the saved path
    public static String captureScreenshot(WebDriver driver, String name) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        String path = "C:\\Users\\2478821\\eclipse-workspace\\FlipkartMobileAutomation\\Screenshot\\" + name + ".png";
        FileUtils.copyFile(src, new File(path));
        return path;
    }

    // Save and close the report
    public static void flushReport() {
        extent.flush();
    }
}