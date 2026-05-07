package utils;

import java.io.File;                                   
import java.io.IOException;                             
import java.time.LocalDateTime;                         
import java.time.format.DateTimeFormatter;             
 
import org.apache.commons.io.FileUtils;                 
import org.openqa.selenium.OutputType;                  
import org.openqa.selenium.TakesScreenshot;             
import org.openqa.selenium.WebDriver;

import configReader.ObjectReader;                   
 
public class ReusableMethod {
	
	// WebDriver instance to interact with the browser
    WebDriver driver;   
    // ObjectReader instance to read values from the properties file
    ObjectReader or;
    // File object representing the destination path for the screenshot
    File dest;          
 
    // Constructor: initializes the driver and loads the properties file via ObjectReader
    public ReusableMethod(WebDriver driver) {
    	// Assign the passed WebDriver to the class variable
        this.driver = driver;
        // Create ObjectReader to access property values
        this.or = new ObjectReader();   
    }
 
    // Builds and returns the full screenshot file path using test name and current timestamp
    public String generateScreenshotPath(String testName) {
    	
    	// Get the current date and time
        String timestamp = LocalDateTime.now()  
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")); // Format it as yyyy-MM-dd_HH-mm-ss
        
        // Combine base path + test name + timestamp + extension
        return or.getScreenShotProperty() + testName + "_" + timestamp + ".png"; 
    }
 
    // Captures a screenshot of the current browser state and saves it to disk
    public String takeScreenshot(WebDriver driver,String testName) throws IOException {
    	
    	// Cast WebDriver to TakesScreenshot to enable screenshot capture
        TakesScreenshot ts = (TakesScreenshot) driver;
        
        // Capture the screenshot and store it as a temporary file
        File srcFile = ts.getScreenshotAs(OutputType.FILE); 
        
        // Generate the destination file path using the test name
        String path = generateScreenshotPath(testName); 
        
        // Create a File object pointing to the destination path
        dest = new File(path); 
        
        dest.getParentFile().mkdirs();
        
        // Copy the temporary screenshot file to the destination path
        FileUtils.copyFile(srcFile, dest); 
        
        return path;
    }
}