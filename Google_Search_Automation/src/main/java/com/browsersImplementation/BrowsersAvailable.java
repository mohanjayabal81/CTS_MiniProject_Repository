package com.browsersImplementation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class BrowsersAvailable {
	
	private WebDriver driver;
	String baseUrl = "https://www.google.com/";
	
	// Launches Chrome Browser
	public WebDriver launchChorme() {
		driver = new ChromeDriver();
		driver.get(baseUrl);
		return driver;
	}
	
	// Launches Edge Browser
	public WebDriver launchEdge() {
		driver = new EdgeDriver();
		driver.get(baseUrl);
		return driver;
	}
}