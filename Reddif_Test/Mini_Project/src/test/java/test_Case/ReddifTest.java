package test_Case;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import ConfigReader.Object_Reader;
import Pages.CreateAccountPage;
import Pages.LandingPage;
import Pages.TermsAndCondtionsPage;
import browser_Implementation.Browsers;

public class ReddifTest {
	WebDriver driver;
	Browsers browser;
	Object_Reader objReader;
	LandingPage lp;
	CreateAccountPage cap;
	TermsAndCondtionsPage tcp;
	private static final String EXP_HEADER = "Create a Rediffmail account";
	private static final String EXP_TITLE = "Terms and Conditions";
	@BeforeSuite
	public void open_Browser() throws Exception
	{
		browser = new Browsers();
		driver = browser.select_Browser();
		objReader = new Object_Reader();
		driver.get(objReader.get_ApplicationUrl());
		lp = new LandingPage(driver);
		cap = new CreateAccountPage(driver);
		tcp = new TermsAndCondtionsPage(driver);

	}
	@Test(priority = 1)
	public void testCreateAccount()
	{
		lp.clickOnCreateAccount();
		Assert.assertEquals(cap.verifyHeader(EXP_HEADER), EXP_HEADER,"Create account is opened");
		Reporter.log("Verified Create Account header successfully", true);
	}
	@Test(priority = 2)
	public void PrintAllTheLinks() {
		List<WebElement> list = cap.getAllLinks();
		Reporter.log("Total links found: " + list.size(), true);
		for(WebElement l:list) {
			System.out.println(l.getAttribute("href"));
		}
	}
	@Test(priority = 3)
	public void verifyChildTitle(){
		cap.clickOnTermsAndConditions();
		cap.switchToChild();
		Assert.assertEquals(tcp.getTileOfTnC(),EXP_TITLE);
		Reporter.log("Verified the Title of the Terms and conditon",true);
		tcp.switchToParent();
	}
	@AfterSuite
	public void close_Browser() {
		browser.closeBrowser();
	}
}
