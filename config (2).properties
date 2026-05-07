package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResultsPage {

	WebDriver driver;
	WebDriverWait wait;

	// Initializes driver and explicit wait.

	public ResultsPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

//	Locators
	By resultHeader = By.xpath("//div[contains(@aria-label,'Result for')]");
	By trainCards = By.xpath("//div[contains(@class,'train-heading')]");
	By trainNameLoc = By.xpath(".//strong");

	// Waits for the train results page to load using explicit wait.

	public void waitForResults() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(resultHeader));
		System.out.println("Train results page loaded");
	}

//	Returns the result header text. Used in test class to verify search criteria
//	like From, To, and Date.

	public String getResultHeaderText() {
		WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(resultHeader));
		return header.getText();
	}

//	Returns a list of train names from the search results. Used in test class to
//	verify results and print train details.

	public List<String> getTrainNames() {
		List<String> trainNames = new ArrayList<>();
		List<WebElement> trains = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(trainCards));

		for (WebElement train : trains) {
			trainNames.add(train.findElement(trainNameLoc).getText().trim());
		}
		return trainNames;
	}
}