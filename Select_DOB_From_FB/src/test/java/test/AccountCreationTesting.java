package test;

import java.io.IOException;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DriverFactory;
import utils.ObjectReader;

public class AccountCreationTesting {

	private WebDriver driver;
	private LoginPage loginPage;
	private ObjectReader objectReader;
	private DriverFactory driverFactory;
	Scanner scanner;

	@BeforeTest
	public void setup() throws IOException {
		// Load property file
		objectReader = new ObjectReader();
		driverFactory = new DriverFactory();
		// Ask user for browser input
		scanner = new Scanner(System.in);
		System.out.print("Enter Browser Name (chrome/edge): ");
		String consoleBrowser = scanner.next();

		// Initialize driver with console input
		driver = driverFactory.initDriver(consoleBrowser);

		// Navigate to URL from properties
		driver.get(objectReader.getUrl());

		// Link driver to Page Object
		loginPage = new LoginPage(driver);

	}

	@Test(priority = 0)
	public void verifyPageTitle() {
		String actualTitle = driver.getTitle();
		String expectedTitle = "Facebook";
		if (actualTitle.equals(expectedTitle)) {
			System.out.println("Successfully landed on the Facebook page.");
		} else {
			System.out.println("Title Mismatch! Expected Facebook but found: " + actualTitle);
		}
	}

	@Test(priority = 1)
	public void createAccount() throws InterruptedException {
		loginPage.openCreateAccount();
		loginPage.setUserName();
		loginPage.setSurname();
		loginPage.enterDay();
		loginPage.enterMonth();
		loginPage.enterYear();
		loginPage.selectGender();
		loginPage.enterMobileNumber();
		loginPage.enterPassword();
		loginPage.submit();
		// Error validations
		WebElement mobileError = driver
				.findElement(By.xpath("//span[contains(text(),'Please enter a valid mobile number or email addres')]"));
		System.out.println("Mobile Error: " + mobileError.getText());
		Reporter.log("Mobile Error: " + mobileError.getText());

		WebElement passwordError = driver
				.findElement(By.xpath("//span[contains(text(),'Enter a combination of at least six numbers, lette')]"));
		System.out.println("Password Error: " + passwordError.getText());
		Reporter.log("Password Error: " + passwordError.getText());
	}

	@AfterTest
	public void tearDown() {
		if (driver != null) {
			scanner.close();
			driver.quit();

		}
	}
}
