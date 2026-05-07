package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ElementUtils;

public class RediffPage {
	
	// WebDriver reference
	WebDriver driver;
	
	//ElementUtils reference
	ElementUtils elementUtils;
	
	// Constructor to receive driver from test class
	public RediffPage(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
	}
	
	// Page elements
	private By checkAvailButton = By.className("btn_checkavail");
	private By suggestedEmailRadioButton = By.id("radio_login");
	private By passwordTextBox = By.id("newpasswd");
	private By retypePasswordTextBox = By.id("newpasswd1");
	private By dobDayDropdown = By.className("day");
	private By dobMonthDropdown = By.xpath("//select[starts-with(@name, 'DOB_Month')]");
	private By dobYearDropdown = By.className("year");
	private By countryDropdown = By.id("country");
	private By cityDropdown = By.xpath("//select[starts-with(@name, 'city')]");
	private By checkAlternateEmail = By.xpath("//input[starts-with(@name, 'chk_altemail')]");
	
	
	// Click on Create Account link and return page title
	public String clickCreateAccount() {
		
		// Find and click Create Account link
		WebElement create = driver.findElement(By.xpath("//a[@title='Create new Rediffmail account']"));
		create.click();
		
		// Get Create Account page title
		WebElement title = driver.findElement(By.xpath("//h2[text()='Create a Rediffmail account']"));
		String actTitle = title.getText();
		return actTitle;
	}
	
	// Enter full name
	public void enterName(String name) {
		
		// Locate name input field and enter name
		WebElement nameInput = driver.findElement(By.xpath("//input[@placeholder='Enter your full name']"));
		nameInput.sendKeys(name);
	}
	
	// Enter email ID
	public void enterEmail(String email) {
		
		// Locate email input field and enter email
		WebElement emailInput = driver.findElement(By.xpath("//input[@placeholder='Enter Rediffmail ID']"));
		emailInput.sendKeys(email);
	}
	
	// Click on Check Availability button
	public void checkAvailability() {
		
		// Click email availability button
		WebElement availElement = driver.findElement(checkAvailButton);
		availElement.click();
	}
	
	// Select auto suggested email using explicit wait
	public void selectSuggestedEmail() {
		
		// Explicit wait declaration
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		// Wait until suggested email option is clickable
		WebElement availableEmail = wait.until(ExpectedConditions.elementToBeClickable(suggestedEmailRadioButton));
		
		// Click suggested email option
		availableEmail.click();
	}
	
	// Enter password
	public void enterPassword(String password) {
		
		// Enter password in password field
		WebElement pass = driver.findElement(passwordTextBox);
		pass.sendKeys(password);
	}
	
	// Re-enter password
	public void reEnterPassword(String password) {
		
		// Re-enter password in confirm password field
		WebElement pass = driver.findElement(retypePasswordTextBox);
		pass.sendKeys(password);
	}
	
	// Check if day dropdown is enabled
	public boolean isDobDayDropdownEnabled() {
		return elementUtils.isElementEnabled(dobDayDropdown);
	}
	
	// Check if month dropdown is enabled
	public boolean isDobMonthDropdownEnabled() {
		return elementUtils.isElementEnabled(dobMonthDropdown);
	}
	
	// Check if year dropdown is enabled
	public boolean isDobYearDropdownEnabled() {
		return elementUtils.isElementEnabled(dobYearDropdown);
	}
	
	// Select Date of Birth from dropdowns
	public void selectDOB(String day, String month, String year) {
		// Select day from dropdown
		WebElement dayElement = driver.findElement(dobDayDropdown);
		Select selectDay = new Select(dayElement);
		selectDay.selectByVisibleText(day);
		
		// Select month from dropdown
		WebElement monthElement = driver.findElement(dobMonthDropdown);
		Select selectMonth = new Select(monthElement);
		selectMonth.selectByVisibleText(month);
		
		// Select year from dropdown
		WebElement yearElement = driver.findElement(dobYearDropdown);
		Select selectYear = new Select(yearElement);
		selectYear.selectByVisibleText(year);

	}
	
	// Select gender radio button
	WebElement genderElement;
	public void selectGender(String gender) {
		// Select gender based on value passed
		genderElement = driver.findElement(By.xpath("//input[@value='" + gender + "']"));
		genderElement.click();
	}
	
	// Check if gender is selected
	public boolean isGenderSelected() {
		return elementUtils.isElementSelected(genderElement);
	}
	
	// Declare dropdown related variables
	List<WebElement> countries;
	WebElement countryElement;
	Select select;
	
	// Fetch and print all country names
	public void printAllCountries() {
		
		// Locate country dropdown
		countryElement = driver.findElement(countryDropdown);

		// Get all options from dropdown
		select = new Select(countryElement);
		countries = select.getOptions();
		
		System.out.println("Available Countries: ");
		
		// Print each country name on console
		for (WebElement country : countries) {
			System.out.println(country.getText());
		}
	}
	
	// Print total number of countries
	public void printCountryCount() {
		
		// Get total number of countries
		int totalCountries = countries.size();
		
		System.out.println("Total number of countries: " + totalCountries);
	}
	
	// Check if country dropdown is enabled
	public boolean isCountryDropdownEnabled() {
		return elementUtils.isElementEnabled(countryDropdown);
	}
	
	// Select country from dropdown
	public void selectCountry(String countryName) {
		
		// Select country using visible text
		select = new Select(countryElement);
		select.selectByVisibleText(countryName);
	}
	
	// Get selected country from dropdown
	public String getSelectedCountry() {
		
		// Get selected option from dropdown
		select = new Select(countryElement);
		WebElement selected = select.getFirstSelectedOption();
		
		String selectedCountry = selected.getText();
		System.out.println("Selected country is: " + selectedCountry);
		// Reporter.log("Selected country is: " + selectedCountry);
		
		return selectedCountry;
	}
	
	// Select city from dropdown
	public void selectCity(String cityName) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement city = wait.until(ExpectedConditions.elementToBeClickable(cityDropdown));
		Select selectCity = new Select(city);
		selectCity.selectByVisibleText(cityName);
	}
	
	// Check if city dropdown is enabled
	public boolean isCityDropdownEnabled() {
		return elementUtils.isElementEnabled(cityDropdown);
	}
	
	public boolean isAlternateIdCheckBox() {
		return elementUtils.isElementEnabled(checkAlternateEmail);
	}
	
	public void alternateIdCheckbox() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement checkAltEmail = wait.until(ExpectedConditions.elementToBeClickable(checkAlternateEmail));
		checkAltEmail.click();
	}
}
