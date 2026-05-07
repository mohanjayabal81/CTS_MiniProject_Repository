package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
	public ExtentReports generateReport() {
		// Initialize the main reporting object
		 ExtentReports e = new ExtentReports();
		 
		// Define the SparkReporter and specify the destination path for the HTML report
		// The report will be saved as 'spark.html' inside the 'target' folder
		 ExtentSparkReporter spark = new ExtentSparkReporter("target/spark.html");
		 
		// Attach the configured reporter to the main ExtentReports instance
		 e.attachReporter(spark);
		 
		// Return the configured object for use in test classes
		 return e;
		 
	}

}
