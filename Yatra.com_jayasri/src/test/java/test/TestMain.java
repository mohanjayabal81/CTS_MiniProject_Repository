
package test;

import org.openqa.selenium.*;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import browserImplementation.BrowserConfig;
import pages.*;
import utils.*;
import com.aventstack.extentreports.*;
import java.util.List;

/*
 * Main Test Class
 * Executes navigation, banner validation and holiday listing
 */
public class TestMain {

    private WebDriver driver;
    private ObjectReader u;
    private BrowserConfig bc;
    private SoftAssert softAssert;

    private HomePage home;
    private OffersPage offers;
    private HolidaysPage holidays;

    private ExtentReports extent;
    private ExtentTest test;

    //Runs once before all tests
    @BeforeClass
    public void setUp() {

        extent = ExtentManager.getInstance();

        bc = new BrowserConfig();
        driver = bc.chooseBrowser();

        u = new ObjectReader();
        softAssert = new SoftAssert();

        home = new HomePage(driver);
        offers = new OffersPage(driver);
        holidays = new HolidaysPage(driver);
    }

   //Navigation & Title Validation
    @Test(priority = 1)
    public void testNavigation() {

        test = extent.createTest("Navigation Test");

        driver.get(u.geturl());
        home.handlePopup();
        home.clickOffers();
        offers.switchToOffersTab();

        if (driver.getTitle().equals(u.get_ExpectedTitle())) {
            test.pass("Title Verified");
            System.out.println("Title Verfied ");        }
        else {
            test.fail("Title Mismatch");
            System.out.println("Title mismatch");
            softAssert.fail();
        }
    }

    //Banner Verification + Screenshot
    @Test(priority = 2)
    public void testBanner() {

        test = extent.createTest("Banner Test");

        if (offers.getBannerText().contains(u.get_ExpectedBanner())) {
            test.pass("Banner Verified");
            System.out.println("Banner Text is verified");
        }
        else {
            test.fail("Banner Mismatch");
            System.out.println("Banner Text is mismatched");
            softAssert.fail();
        }

        String path =ScreenshotUtils.captureScreenshot(driver,"yatra_requirement2");

        test.addScreenCaptureFromPath(path);
    }

    //Holiday Packages Validation & Name Listing
    @Test(priority = 3)
    public void testHolidayPackages() {

        test = extent.createTest("Holiday Packages Test");

        // Retrieve the list of elements using the CSS selector from your properties
        List<WebElement> packages = holidays.getHolidayPackages();

        if (packages.size() > 0) {
            test.pass("Packages Found: " + packages.size());
            
            // Start a list in the Extent Report
            test.info("<b>Available Holiday Packages:</b>");
            
            System.out.println("Packages Found: " + packages.size());
            for (WebElement pkg : packages) {
                String name = pkg.getText().trim();
             
                // Only print if the text is not empty
                if (!name.isEmpty()) {
                    test.info("Package Name: " + name);
                    System.out.println("*"+name);
                }
            }
        } else {
            test.fail("No Packages Found");
            softAssert.fail("Package list was empty.");
        }

        softAssert.assertAll();
    }

    /*
     * Runs after all tests
     */
    @AfterClass
    public void tearDown() {
            bc.closeBrowser();

        extent.flush();  // Generates report
    }
}