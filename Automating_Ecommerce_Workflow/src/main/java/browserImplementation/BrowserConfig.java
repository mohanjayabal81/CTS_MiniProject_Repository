package browserImplementation;
 
import java.util.Scanner;
 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import Utils.ConfigReader;
 
public class BrowserConfig {
	
	
	WebDriver driver;
	public WebDriver chooseBrowser() {
		 	     
			
	       
	                driver = new ChromeDriver(); 
	            
	            
	        driver.manage().window().maximize();
	        ConfigReader.initProperties();
	        driver.get(ConfigReader.getProperty("url"));
       
         return driver;
	}
	 public void closeBrowser() {
         driver.quit();
}
	
}