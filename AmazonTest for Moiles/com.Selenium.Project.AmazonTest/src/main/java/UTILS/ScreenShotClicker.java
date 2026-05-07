package UTILS;

import java.io.File;                                   
import java.io.IOException;                             
import java.time.LocalDateTime;                         
import java.time.format.DateTimeFormatter;             

import org.apache.commons.io.FileUtils;                 
import org.openqa.selenium.OutputType;                  
import org.openqa.selenium.TakesScreenshot;             
import org.openqa.selenium.WebDriver;                   

public class ScreenShotClicker {

    private WebDriver driver;   // WebDriver instance to interact with the browser
    
    private ObjectReader or;    // ObjectReader instance to read values from the properties file
    
    private File dest;          // File object representing the destination path for the screenshot

    // Constructor: initializes the driver and loads the properties file via ObjectReader
    public ScreenShotClicker(WebDriver driver) throws IOException {
        this.driver = driver;   // Assign the passed WebDriver to the class variable
        
        this.or = new ObjectReader();   // Create ObjectReader to access property values
    }

    // Builds and returns the full screenshot file path using test name and current timestamp
    public String generateScreenshotPath(String testName) {
        String timestamp = LocalDateTime.now()  // Get the current date and time
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")); // Format it as yyyy-MM-dd_HH-mm-ss
        return or.getScreenShotProperty() + testName + "_" + timestamp + ".png"; // Combine base path + test name + timestamp + extension
    }

    // Captures a screenshot of the current browser state and saves it to disk
    public String takeScreenshot(String testName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver; // Cast WebDriver to TakesScreenshot to enable screenshot capture
        File srcFile = ts.getScreenshotAs(OutputType.FILE); // Capture the screenshot and store it as a temporary file
        String path = generateScreenshotPath(testName); // Generate the destination file path using the test name
        dest = new File(path); // Create a File object pointing to the destination path
        FileUtils.copyFile(srcFile, dest); 
        return path;// Copy the temporary screenshot file to the destination path
    }
}