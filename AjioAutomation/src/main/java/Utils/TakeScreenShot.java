package Utils;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

public class TakeScreenShot {

	public String captureScreenshot(WebDriver driver, String screenshotName) throws IOException {
		// Convert the driver into a 'TakesScreenshot' object
	    TakesScreenshot ts = (TakesScreenshot) driver;
	    
	    // Capture the image and store it in a temporary 'source' file
	    File src = ts.getScreenshotAs(OutputType.FILE);
	    
	    // Define the destination path where the image will be permanently saved
	    // It uses the project folder (user.dir) and puts it into a 'screenshots' sub-folder
	    String path = System.getProperty("user.dir") + "/screenshots/" + screenshotName + ".png";
	    
	    // Create a file object for the destination and move the image from temporary storage to the folder
	    File dest = new File(path);
	    FileUtils.copyFile(src, dest);
	    
	    //return path of the screenshot
	    return path;
	}

}
