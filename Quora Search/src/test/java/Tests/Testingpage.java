package Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import browserImplementation.BrowserConfig_Reader;
import configReader.ConfigReader;
import pages.Landingpage;

public class Testingpage {

    private WebDriver driver;
    private BrowserConfig_Reader br;
    private Landingpage lp;
    private ConfigReader cr;

    // ExtentReports fields
    private ExtentReports extent;
    private ExtentTest extentTest;
 
     //Captures a screenshot, saves it to the screenshots/ folder,
 
    private String captureScreenshot(String testName) {
        String screenshotPath = "";
        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String fileName = "screenshots/" + testName + "_" + System.currentTimeMillis() + ".png";
            File destFile = new File(fileName);
            destFile.getParentFile().mkdirs();          // create folder if absent
            FileUtils.copyFile(srcFile, destFile);
            screenshotPath = destFile.getAbsolutePath();
        } catch (IOException e) {
            System.out.println("Screenshot capture failed: " + e.getMessage());
            System.out.println("Screenshot capture failed: " + e.getMessage());
        }
        return screenshotPath;
    }

    private void logPass(ExtentTest test, String message, String testName) throws IOException {
        String path = captureScreenshot(testName);
        test.log(Status.PASS, message,MediaEntityBuilder.createScreenCaptureFromPath(path).build());
    }

    
     // Logs a FAIL entry with an embedded screenshot into the ExtentTest node.
     
    private void logFail(ExtentTest test, String message, String testName) throws IOException {
        String path = captureScreenshot(testName);
        test.log(Status.FAIL, message,
                MediaEntityBuilder.createScreenCaptureFromPath(path).build());
    }


    @BeforeTest
    public void Browser_Launch() throws InterruptedException {

        // Initialize ExtentReports with SparkReporter
        ExtentSparkReporter spark = new ExtentSparkReporter("reports/ExtentReport.html");
        spark.config().setReportName("Quora Automation Report");
        spark.config().setDocumentTitle("Test Results");

        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Tester", "QA Team");
        extent.setSystemInfo("Application", "Quora");

        // Browser setup
        br = new BrowserConfig_Reader(driver);
        int browser = br.get_Browser();

        if (browser == 1) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-blink-features=AutomationControlled");
            driver = br.get_chrome(options);
        } else {
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--disable-blink-features=AutomationControlled");
            driver = br.get_Edge(options);
        }

        br.Launch_Url();
        Thread.sleep(15000);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        lp = new Landingpage(driver);
    }
    @Test
    public void Check_SignIn() throws IOException, InterruptedException {
        extentTest = extent.createTest("Check_SignIn", "Verify successful sign-in and URL match");
        try {
            String currentUrl = lp.Sign_In();
            String ExUrl = "https://www.quora.com/";
            Assert.assertEquals(currentUrl, ExUrl, "Url not Matched");
            logPass(extentTest, "Sign-In successful. URL matched: " + currentUrl, "Check_SignIn");
            Reporter.log("sign_In-Testcase passed");
        } catch (AssertionError | Exception e) {
            logFail(extentTest, "Sign-In failed: " + e.getMessage(), "Check_SignIn");
            throw e;
        }
    }

    @Test(dependsOnMethods = "Check_SignIn")
    public void Check_Text() throws IOException, InterruptedException {
        extentTest = extent.createTest("Check_Text", "Verify expected text content on the page");
        try {
            cr = new ConfigReader();
            String ACtext = lp.Text();
            String EXtext = cr.get_Expectedcontent();
            Assert.assertEquals(ACtext, EXtext, "Title does not match!");
            logPass(extentTest, "Text verified successfully. Actual: " + ACtext, "Check_Text");
            Reporter.log("Check text-passed");
        } catch (AssertionError | Exception e) {
            logFail(extentTest, "Text verification failed: " + e.getMessage(), "Check_Text");
            throw e;
        }
    }

    @Test(dependsOnMethods = "Check_Text")
    public void Check_SignupButtonEnabled() throws InterruptedException, IOException {
        extentTest = extent.createTest("Check_SignupButtonEnabled",
                "Verify login button is disabled initially");
        try {
            lp.Logout();
            WebElement checkLoginButton = lp.get_Loginbutton();
            Assert.assertFalse(checkLoginButton.isEnabled());
            logPass(extentTest, "Login button is correctly disabled on page load",
                    "Check_SignupButtonEnabled");
            Reporter.log("Login Button verified");
        } catch (AssertionError | Exception e) {
            logFail(extentTest, "Login button check failed: " + e.getMessage(),
                    "Check_SignupButtonEnabled");
            throw e;
        }
    }

    @Test(dependsOnMethods = "Check_SignupButtonEnabled")
    public void CheckTextforInvalidEmail() throws InterruptedException, IOException {
        extentTest = extent.createTest("CheckTextforInvalidEmail",
                "Verify error text shown for invalid email");
        try {
            lp.checkEmailText();
            logPass(extentTest, "Invalid email error text verified successfully",
                    "CheckTextforInvalidEmail");
            Reporter.log("Filling Incorrect Email - check obtained the Result text - passed");
        } catch (AssertionError | Exception e) {
            logFail(extentTest, "Invalid email check failed: " + e.getMessage(),
                    "CheckTextforInvalidEmail");
            throw e;
        }
    }
    @AfterTest
    public void Browser_close() {
        br.Close_Browser();
        extent.flush();   // writes the final HTML report
    }
}