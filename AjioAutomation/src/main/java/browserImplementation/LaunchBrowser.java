package browserImplementation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class LaunchBrowser {
	WebDriver driver;
	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	public WebDriver getDriver() {
		return driver;
	}
	
	public WebDriver getChromeBrowser() {
		driver = new ChromeDriver();
		return driver;
	}
	
	public WebDriver getEdgeBrowser() {
		driver = new EdgeDriver();
		return driver;
	}

}
