package browser;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class BrowserManager {
	private WebDriver driver;

	// ask the user to enter the browser name
	public String selectBrowser() {
		System.out.println("Enter the Browser Name (chrome/edge) :- ");
		Scanner sc = new Scanner(System.in);
		String browserName = sc.next();
		sc.close();
		return browserName;
	}

	// returns chrome Browser instance
	public WebDriver getChrome() {
		return new ChromeDriver();
	}

	// returns edge Browser instance
	public WebDriver getEdge() {
		return new EdgeDriver();
	}
	
	// maximizes the browser window
	public void maximizeBrowser(WebDriver driver) {
		this.driver=driver;
		driver.manage().window().maximize();
	}

	// closes the browser window
	public void closeDriver(WebDriver driver) {
		this.driver = driver;
		driver.quit();
	}

}
