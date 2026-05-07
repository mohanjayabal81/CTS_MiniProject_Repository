package tests;
import base.Browsers;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;
import pages.AlertsPage;
import utils.ObjectReader;
import utils.ScreenshotUtils;
import java.io.IOException;

public class AlertsPageTest {
    WebDriver driver;
    Browsers browsers;
    ObjectReader or;
    AlertsPage alertsPage;
    ScreenshotUtils screenshotUtils=new ScreenshotUtils();

    //  browser setup
    @Parameters("browser")
    @BeforeClass
    public void setUp(String browser) throws IOException {
        try{
            browsers=new Browsers();
            or=new ObjectReader();
            driver=browsers.getDriver(browser);
            driver.manage().window().maximize();
            String url=or.getUrl();
            driver.get(url);
            Reporter.log("Navigated to URL: " + url, true);
            alertsPage=new AlertsPage(driver);
        }catch (Exception e){
            Reporter.log("Setup failed"+e.getMessage(),true);
            Assert.fail("Setup failed"+e.getMessage());
        }
    }

    //    navigate to alerts page and validate alerts page by title
    @Test(priority=0)
    public void validateAlertsPage() {
        try{
            alertsPage.navigateToAlertsPage();
            String acTitle=driver.getTitle();
            Assert.assertEquals(acTitle,or.getExpectedTitle());
            Reporter.log("PASSED - Alerts page verified. Title: " + acTitle, true);
        }catch(Throwable e){
            screenshotUtils.takeScreenShot(driver,"validateAlertsPage");
            Reporter.log("Failed - title mismatch. Title: " + e.getMessage(), true);
            Assert.fail("Failed - title mismatch. Title: " + e.getMessage());
        }
    }

    //    handle simple alert test case
    @Test(priority = 1)
    public void simpleAlertTestCase() throws IOException {
        try{
            Reporter.log("Clicked on Simple Alert button", true);
            String expSimpleAlertMsg=or.getSimpleAlertExpMsg();
            String simpleAlertMsg=alertsPage.handleSimpleAlert(or.getSimpleAlertXpath());
            Reporter.log("Expected: " + expSimpleAlertMsg + " | Actual: " + simpleAlertMsg, true);
            Assert.assertEquals(simpleAlertMsg,expSimpleAlertMsg);
            Reporter.log("PASSED – Simple alert message verified", true);
        } catch (Throwable e){
            screenshotUtils.takeScreenShot(driver,"simpleAlertTestCase");
            Reporter.log("FAILED "+e.getMessage(),true);
            Assert.fail("FAILED "+e.getMessage());
        }
    }

    //    handle confirm alert test case
    @Test(priority = 2)
    public void confirmAlertTestCase() throws IOException {
        try{
            Reporter.log("Handling Confirmation Alert", true);
            String expConfirmAlertMsg=or.getConfirmAlertExpMsg();
            String acConfirmAlertMsg=alertsPage.handleConfirmationAlert(or.getConfirmationAlertXpath());
            Reporter.log("Expected: " + expConfirmAlertMsg + " | Actual: " + acConfirmAlertMsg, true);
            Assert.assertEquals(acConfirmAlertMsg,expConfirmAlertMsg);
            Reporter.log("PASSED – Confirmation alert message verified", true);
        } catch (Throwable e){
            screenshotUtils.takeScreenShot(driver,"confirmAlertTestCase");
            Reporter.log("FAILED "+e.getMessage(),true);
            Assert.fail("FAILED "+e.getMessage());
        }
    }

    //    handle prompt alert test case
    @Test(priority = 3)
    public void promptAlertTestCase() throws IOException {
        try{
            Reporter.log("Handling Prompt Alert", true);
            String name=or.getName();
            String expPromptAlertMsg=or.getPromptAlertExpMsg();
            String promptAlertMsg=alertsPage.handlePromptAlert(name,or.getPromptAlertXpath());
            Reporter.log("Expected: " + expPromptAlertMsg + " | Actual: " + promptAlertMsg, true);
            Assert.assertEquals(promptAlertMsg,expPromptAlertMsg);
            Reporter.log("PASSED – Prompt alert message verified", true);
        }catch (Throwable e){
            screenshotUtils.takeScreenShot(driver,"promptAlertTestCase");
            Reporter.log("FAILED "+e.getMessage(),true);
            Assert.fail("FAILED "+e.getMessage());
        }
    }

    //    close browser
    @AfterClass
    public void tearDown(){
        driver.quit();
        Reporter.log("Browser closed successfully", true);
    }
}