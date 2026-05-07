package browserImplementation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class BrowserConfiguartion {
	WebDriver driver;
	
	
	
	//method to launch browser
	public WebDriver Launch_Chrome_Browser() {
		
		//To open chrome browser
		driver=new ChromeDriver();
		return driver;
	}
	
	/*
	//method to launch browser
	public WebDriver Launch_Edge_Browser() {
		
		//To open chrome browser
		driver=new EdgeDriver();
		return driver;
	}
	
	
	
	//method to launch browser
	public WebDriver Launch__Browser(String browser) {
		
		if(browser.equals("edge")) {
			//To open chrome browser
			driver=new EdgeDriver();
			return driver;
		}
		else if(browser.equals("chrome")) {
			//To open chrome browser
			driver=new ChromeDriver();
			return driver;
		}
		return driver;
	}
	*/
}
