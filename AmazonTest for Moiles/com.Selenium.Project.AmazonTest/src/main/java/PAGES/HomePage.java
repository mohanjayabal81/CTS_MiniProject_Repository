// Declares the package name, grouping this class under 'PAGES'
package PAGES;

// Imports IOException to handle potential errors when reading from files or external resources
import java.io.IOException;

// Imports the 'By' class from Selenium, used to locate web elements using various strategies (id, name, xpath, etc.)
import org.openqa.selenium.By;

// Imports the WebDriver interface from Selenium, used to control the browser
import org.openqa.selenium.WebDriver;

// Imports the WebElement interface, representing an HTML element on the web page
import org.openqa.selenium.WebElement;

// Imports the ObjectReader utility class, used to read test data and locators from external sources (e.g., properties file)
import UTILS.ObjectReader;

// Declares a public class named 'HomePage' representing the Home Page of the application under test
public class HomePage {

	// Declares a private WebDriver instance to interact with the browser
	private WebDriver driver;

	// Declares a private ObjectReader instance to fetch locators and test data from external config/properties files
	private ObjectReader or;

	// Constructor that accepts a WebDriver object and initializes the driver and ObjectReader instances
	// Throws IOException in case the ObjectReader fails to read from the properties/config file
	public HomePage(WebDriver driver) throws IOException {
		// Assigns the passed WebDriver instance to the class-level 'driver' variable
		this.driver = driver;

		// Creates a new ObjectReader instance to load locators and test data from external files
		or = new ObjectReader();
	}

	// Method that retrieves and returns the base URL of the application from the ObjectReader
	public String getHomeUrl() {
		// Calls getBaseUrl() on the ObjectReader to fetch the application's base URL
		return or.getBaseUrl();
	}

	// Method that retrieves and returns the expected title of the Home Page from the ObjectReader
	public String verify_Title() {
		// Calls getHomeTitle() on the ObjectReader to fetch the expected home page title for verification
		return or.getHomeTitle();
	}

	// Method that locates and returns the search box WebElement on the Home Page
	public WebElement searchBoxLocate() {
		// Finds the search box element on the page using its ID, which is retrieved from the ObjectReader
		return driver.findElement(By.id(or.getSearchObject()));
	}

	// Method that performs a product search by entering a keyword and clicking the search button
	public void searchProduct(WebElement searchBox) {
		// Types the search keyword (fetched from ObjectReader) into the provided search box element
		searchBox.sendKeys(or.getSearchKeyValue());

		// Locates the search button by its ID (fetched from ObjectReader) and clicks it to trigger the search
		driver.findElement(By.id(or.getSearchButtonObject())).click();
	}
}