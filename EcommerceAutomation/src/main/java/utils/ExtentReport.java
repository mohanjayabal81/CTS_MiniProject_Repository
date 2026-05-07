package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
 
	public ExtentReports reportGenerator() {
		ExtentReports extent = new ExtentReports(); 
		ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReportSpark.html"); 
		spark.config().setReportName("Automation Report");
		extent.attachReporter(spark); 
		extent.setSystemInfo("os", "Windows");
		return extent;
	}
}