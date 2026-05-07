package com.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TestUtilities {
	public static int screenshotCounter = 1;

	public void takeScreenshotOfElement(String name, WebElement element) throws IOException {
		//creates the screenshot folder in the root if not already created
		Files.createDirectories(Paths.get("screenshots"));
		
		//creating screenshot file and storing it in the screenshot folder
		File src = ((TakesScreenshot) element).getScreenshotAs(OutputType.FILE);
		File dest = new File("screenshots/", screenshotCounter++ + "_" + name + ".png");

		FileUtils.copyFile(src, dest);
		System.out.println("Screenshot saved: " + dest);
	}


	public void takeFullPageScreenshot(String name, WebDriver driver) throws IOException, InterruptedException {
		// creates screenshot folder in the root
		Files.createDirectories(Paths.get("screenshots"));

	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    // capture screenshot of page top
	    js.executeScript("window.scrollTo(0, 0);");
	    
	    File topSrc = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    File topDest = new File("screenshots/", screenshotCounter++ + "_" + name + ".png");
	    FileUtils.copyFile(topSrc, topDest);
	    
	    System.out.println("Full-page screenshot saved: " + topDest.getAbsolutePath());

	    // capture screenshot of page bottom
	    js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	    
	    File bottomSrc = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    File bottomDest = new File("screenshots/", screenshotCounter++ + "_" + name + "_bottom.png");
	    FileUtils.copyFile(bottomSrc, bottomDest);
	    
	    System.out.println("Bottom screenshot saved: " + bottomDest.getAbsolutePath());

	    // reset to top
	    js.executeScript("window.scrollTo(0, 0);");
	}
}
