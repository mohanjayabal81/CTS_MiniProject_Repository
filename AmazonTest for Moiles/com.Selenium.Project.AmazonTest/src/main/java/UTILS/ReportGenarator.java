package UTILS;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportGenarator {

	public ExtentReports reportGenerator() {
		ExtentReports extent = new ExtentReports(); 
		ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark.html"); 
		extent.attachReporter(spark); 
		extent.setSystemInfo("os", "Windows");
		return extent;
	}
}
