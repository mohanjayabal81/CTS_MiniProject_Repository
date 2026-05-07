package utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilities {
	
	// capture full page screenshot
	public String captureScreenshot(WebDriver driver,String screenshotName) throws IOException {
		
		// Cast WebDriver to TakesScreenshot interface
		TakesScreenshot ts= (TakesScreenshot) driver;
		
		String path=reusableMethod(ts,screenshotName);
		
		// returns the screenshot path for reporting
		return path;
	}
	
	// capture web element screenshot
	public String captureScreenshot(WebElement element,String screenshotName) throws IOException {
		
		TakesScreenshot ts= (TakesScreenshot) element;
		
		String path=reusableMethod(ts,screenshotName);
		
		return path;
	}
	
	public String reusableMethod(TakesScreenshot ts,String screenshotName) throws IOException {
		
		// Build screenshot file path inside screenshots folder
		String path= System.getProperty("user.dir")+ "/screenshots/" + screenshotName +".png";
		
		// Capture screenshot as a temporary file
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);
		
		// Destination file where screenshot will be saved
		File destFile = new File(path);
		
		// Copy screenshot file to destination
		FileUtils.copyFile(sourceFile,destFile);
		return path;
	}
	
	
	public void waitExplicitly(WebDriver driver) {
		
		// Create explicit wait with 3 seconds timeout
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(3));
		
		try {
			// Wait until a condition that never becomes true
			wait.until((WebDriver d)->{
				return false;
			});
		}
		catch(TimeoutException e) {
			// intentional pause
			// Timeout is expected and ignored to create a pause
		}
	}

}
