package testcases;
import java.io.IOException;
import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Utils.WaitUtils;
import browser.OpenBrowser;
import pages.SnapdealPage;

@Listeners(listeners.TestListener.class)


public class SnapdealTest {

    // WebDriver instance
    private WebDriver driver;
    WaitUtils waitUtils ;

    // Snapdeal page object
    SnapdealPage snapdeal;
    // Launch browser and initialize page objects
    @BeforeClass
    
    public void setup() throws IOException {

        
        System.out.println("Enter Browser Name (chrome/edge): ");
        Scanner sc = new Scanner(System.in);
        String browserName = sc.nextLine();

        if (browserName.equalsIgnoreCase("chrome")) {
            setDriver(OpenBrowser.chromeBrowser());
        }
        else if (browserName.equalsIgnoreCase("edge")) {
            setDriver(OpenBrowser.edgeBrowser());
        }
        else {
        	sc.close();
            throw new RuntimeException("Invalid browser name");
        }
        sc.close();

        snapdeal = new SnapdealPage(getDriver());
        
    }
    // Verify Snapdeal landing page is displayed
    @Test(priority = 1)
    public void verifySnapdealLandingPage() {
        Assert.assertTrue(snapdeal.isLandingPageDisplayed());
        Reporter.log("Landing Page Verification Successful");
    }

    // Complete Snapdeal product search flow
    @Test(priority = 2)
    public void snapdealFlowTest() throws InterruptedException {

        // Search product
        snapdeal.searchProduct("Bluetooth HeadPhones");
        Reporter.log("HeadPhones Search Successfully");

        // Apply price filter
        snapdeal.applyPriceFilter("700", "900");
        

        // Sort products low to high
        snapdeal.sortByLowToHigh();
        
        waitUtils = new WaitUtils();
        waitUtils.waitExplicitly(driver);

        // Print top 5 products
        snapdeal.printTop5Products();
    }

    // Close browser after tests
    @AfterClass
    public void tearDown() {
        getDriver().quit();
    }

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
}