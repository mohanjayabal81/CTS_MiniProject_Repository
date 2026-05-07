package pages;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.JSUtil;

public class HomePage {

	WebDriver driver;
	WebDriverWait wait;

	// Initializes driver and explicit wait.

	public HomePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

//	Locators
	By fromInput = By.cssSelector("input[aria-autocomplete='list']");
	By toInput = By.xpath("(//input[@aria-autocomplete='list'])[2]");
	By calendarGroup = By.id("jDate");
	By classDropdown = By.id("journeyClass");
	By dropdownPanel = By.cssSelector("div.ui-dropdown-items-wrapper");
	By pwdCheckbox = By.xpath("//label[normalize-space()='Person With Disability Concession']");
	By pwdOkButton = By.xpath("//button[contains(@class,'ui-confirmdialog-acceptbutton')]//span[text()='OK']");
	By searchButton = By.xpath("//button[normalize-space()='Search Trains']");

	// Returns the current page title. Used in test class to verify correct page has
	// opened.

	public String getPageTitle() {
		return driver.getTitle();
	}

	// Returns the current page URL. Used in test class to verify correct page has
	// opened.

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	// Logs into IRCTC using the menu login popup.

	public void login(String userName, String password) {
		driver.findElement(By.xpath("//div[@class='h_menu_drop_button hidden-xs']//i[@class='fa fa-align-justify']"))
				.click();
		driver.findElement(By.xpath("//button[@class='search_btn']")).click();

		try {
			By loginPopup = By.cssSelector("div.ui-dialog-content");
			WebElement userNameField = driver.findElement(By.cssSelector("input[formcontrolname='userid']"));
			WebElement passwordField = driver.findElement(By.cssSelector("input[formcontrolname='password']"));
			WebElement signInButton = driver.findElement(By.xpath("//button[normalize-space()='SIGN IN']"));

			wait.until(ExpectedConditions.visibilityOfElementLocated(loginPopup));

			// Wait and enter username
			wait.until(ExpectedConditions.elementToBeClickable(userNameField));
			userNameField.sendKeys(userName);

			// Wait and enter password
			wait.until(ExpectedConditions.elementToBeClickable(passwordField));
			passwordField.sendKeys(password);

			// Wait and click sign in
			WebElement signIn = wait.until(ExpectedConditions.elementToBeClickable(signInButton));
			JSUtil.clickByJS(driver, signIn);

			System.out.println("Login attempted for user: " + userName);

		} catch (Exception e) {
			System.out.println("Login failed: " + e.getMessage());
		}
	}

	// Handles browser alert popup if present. Accepts the alert and continues
	// silently if no alert is found. Required as per case study — alerts may appear
	// on IRCTC on launch.

	public void handleAlert() {
		try {
			wait.until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert().accept();
		} catch (Exception e) {
			// No alert present, continue to next step
		}
	}

//	Enters From and To stations and selects from autocomplete suggestions.
//	Station values are passed from the test class via config, keeping this method reusable.

	public void enterStations(String fromStation, String toStation) {
		WebElement from = wait.until(ExpectedConditions.elementToBeClickable(fromInput));
		from.click();
		from.sendKeys(fromStation);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'" + fromStation + "')]")))
				.click();

		WebElement to = wait.until(ExpectedConditions.elementToBeClickable(toInput));
		to.click();
		to.sendKeys(toStation);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'" + toStation + "')]")))
				.click();

		System.out.println("Stations entered: " + fromStation + " to " + toStation);
	}

	// Selects journey date by adding the given number of days from today.
	// Navigates calendar forward until target month and year are displayed.

	public void selectJourneyDate(int daysToAdd) {
		WebElement dateInputField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//input[@class='ng-tns-c69-9 ui-inputtext ui-widget ui-state-default ui-corner-all ng-star-inserted']")));
		dateInputField.click();

		LocalDate targetDate = LocalDate.now().plusDays(daysToAdd);
		String targetMonth = targetDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
		int targetYear = targetDate.getYear();
		int targetDay = targetDate.getDayOfMonth();

		wait.until(ExpectedConditions.visibilityOfElementLocated(calendarGroup));

		// Navigate calendar forward until the target month and year are visible
		while (true) {
			WebElement calendar = driver.findElement(calendarGroup);
			String displayedMonth = calendar.findElement(By.cssSelector("span.ui-datepicker-month")).getText();
			String displayedYear = calendar.findElement(By.cssSelector("span.ui-datepicker-year")).getText();

			if (displayedMonth.equalsIgnoreCase(targetMonth) && Integer.parseInt(displayedYear) == targetYear)
				break;

			calendar.findElement(By.cssSelector("a.ui-datepicker-next")).click();
			wait.until(ExpectedConditions.stalenessOf(calendar));
			wait.until(ExpectedConditions.visibilityOfElementLocated(calendarGroup));
		}

		By dayLocator = By
				.xpath("//td[not(contains(@class,'ui-datepicker-other-month'))]//a[text()='" + targetDay + "']");
		wait.until(ExpectedConditions.elementToBeClickable(dayLocator)).click();

		System.out.println("Journey date selected: " + targetDate);
	}

	// Selects the journey class from the All Classes dropdown.

	public void selectJourneyClass(String className) {
		wait.until(ExpectedConditions.elementToBeClickable(classDropdown)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownPanel));

		By classOption = By.xpath("//li[@role='option']//span[text()='" + className + "']");
		wait.until(ExpectedConditions.elementToBeClickable(classOption)).click();

		System.out.println("Journey class selected: " + className);
	}

//	Selects Person With Disability Concession checkbox.
//	Handles the confirmation popup by clicking OK.
//	Waits for popup to fully close before proceeding.

	public void selectPWDConcession() {
		wait.until(ExpectedConditions.elementToBeClickable(pwdCheckbox)).click();
		wait.until(ExpectedConditions.elementToBeClickable(pwdOkButton)).click();
		// Wait for PWD popup to close before proceeding
		wait.until(ExpectedConditions.invisibilityOfElementLocated(pwdOkButton));
		System.out.println("PWD Concession selected and confirmed");
	}

//	Clicks the Search Trains button. Falls back to JS click if normal click is
//	intercepted by an overlay.

	public void clickSearchTrains() {
		WebElement button = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
		try {
			button.click();
		} catch (org.openqa.selenium.ElementClickInterceptedException e) {
			// Use JS click if an overlay is blocking the button
			JSUtil.clickByJS(driver, button);
			System.out.println("Used JS click for Search Trains button");
		}
		System.out.println("Search Trains button clicked");
	}
}