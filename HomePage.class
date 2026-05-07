package utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

// Initializes WebDriver based on browser type from config.

public class DriverFactory {

	public static WebDriver getDriver(String browserName) {

		WebDriver driver = null;

		switch (browserName.toLowerCase().trim()) {

		case "chrome":
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--disable-notifications");
			driver = new ChromeDriver(chromeOptions);
			break;

		case "edge":
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.addArguments("--disable-notifications");
			driver = new EdgeDriver(edgeOptions);
			break;

		default:
			System.out.println("Browser '" + browserName + "' not supported. Defaulting to Chrome.");
			driver = new ChromeDriver();
			break;
		}

		driver.manage().window().maximize();
		// Implicit wait applied once after browser launch
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		System.out.println("Browser launched: " + browserName);

		return driver;
	}
}