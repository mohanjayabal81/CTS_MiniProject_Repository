
package utils;

import java.io.File; 
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {
            // FIX: Removed the extra parentheses and used File.separator for stability
            String folderPath = System.getProperty("user.dir") + File.separator + "Reports";
            String filePath = folderPath + File.separator + "ExtentReport.html";

            // Ensure the directory exists before creating the file
            File reportDir = new File(folderPath);
            if (!reportDir.exists()) {
                reportDir.mkdirs();
            }

            ExtentSparkReporter spark = new ExtentSparkReporter(filePath);

            spark.config().setReportName("Yatra Automation Report");
            extent = new ExtentReports();
            extent.attachReporter(spark);
        }

        return extent;
    }
}