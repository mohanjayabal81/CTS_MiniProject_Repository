// Declares the package name, grouping this class under 'BrowserImplementation'
package BrowserImplementation;

// Imports the WebDriver interface from Selenium, used to control the browser
import org.openqa.selenium.WebDriver;

// Imports the ChromeDriver class to enable launching and controlling Google Chrome
import org.openqa.selenium.chrome.ChromeDriver;

// Imports the FirefoxDriver class to enable launching and controlling Mozilla Firefox
import org.openqa.selenium.firefox.FirefoxDriver;

// Declares a public class named 'BrowserLaunch' responsible for browser initialization
public class BrowserLaunch {

	// Declares a private WebDriver variable 'driver' to hold the browser driver instance
	private WebDriver driver;

	// Getter method that returns the current WebDriver instance stored in 'driver'
	public WebDriver getDriver() {
		// Returns the driver object to the caller
		return driver;
	}

	// Setter method that assigns an external WebDriver instance to the 'driver' variable
	public void setDriver(WebDriver driver) {
		// Sets the local 'driver' field to the WebDriver object passed as a parameter
		this.driver = driver;
	}

	// Method to initialize and return a ChromeDriver instance for Google Chrome
	public WebDriver chromeLaunch() {
		// Creates a new ChromeDriver instance and assigns it to the 'driver' variable,
		// which also launches a new Google Chrome browser window
		driver = new ChromeDriver();

		// Returns the initialized ChromeDriver instance to the caller
		return driver;
	}

	// Method to initialize and return a FirefoxDriver instance for Mozilla Firefox
	public WebDriver fireFoxLaunch() {
		// Creates a new FirefoxDriver instance and assigns it to the 'driver' variable,
		// which also launches a new Mozilla Firefox browser window
		driver = new FirefoxDriver();

		// Returns the initialized FirefoxDriver instance to the caller
		return driver;
	}

}