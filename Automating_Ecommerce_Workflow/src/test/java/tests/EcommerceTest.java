 package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Pages.CartPage;
import Pages.CheckoutPage;
import Pages.HomePage;
import Pages.Login;
import Pages.ProductPage;
import Utils.ExcelUtils;
import browserImplementation.BrowserConfig;

public class EcommerceTest {

    private static String username = "Bharathraj";
    private static String password = "2004";

    WebDriver driver;
    private static ExtentReports extent;
    private static ExtentTest test;

    @BeforeSuite
    public void setUp() {
        extent = new ExtentReports();
        extent.attachReporter(new ExtentSparkReporter("reports/ExtentReport.html"));

        BrowserConfig bc = new BrowserConfig();
        driver = bc.chooseBrowser();
    }

    @Test(priority = 1)
    public void loginFlowTest() {
        test = extent.createTest("Login Flow Test");
        Login login = new Login(driver);
        login.clkBtn();
        login.enterCredentials(username, password);
        login.clicksup();

        String str = login.verifyLogin();
        Assert.assertEquals("Welcome " + username, str);
        System.out.println("login is Successful...!");
    }

    @Test(priority = 2)
    public void productSearchFlow() {
        test = extent.createTest("Product Search Flow");
        HomePage home = new HomePage(driver);
        home.selectFirstProduct();
        ProductPage product = new ProductPage(driver);
        product.addProductToCart();
    }

    @Test(priority = 3)
    public void cartCheckOutFlow() throws InterruptedException, IOException {
        test = extent.createTest("Cart Checkout Flow");
        CartPage cart = new CartPage(driver);
        cart.openCart();
        cart.clickPlaceOrder();
        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.enterDetails(
            ExcelUtils.getData("Sheet1", 1, 0),
            ExcelUtils.getData("Sheet1", 1, 1),
            ExcelUtils.getData("Sheet1", 1, 2),
            ExcelUtils.getData("Sheet1", 1, 3),
            ExcelUtils.getData("Sheet1", 1, 4),
            ExcelUtils.getData("Sheet1", 1, 5)
        );
        checkout.clickPurchase();
        String screenshotPath = checkout.takeScreenshot();
        test.addScreenCaptureFromPath(screenshotPath, "Order Confirmation");
    }

    @AfterMethod
    public void handleTestResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE)
            test.log(Status.FAIL, result.getThrowable().getMessage());
        else if (result.getStatus() == ITestResult.SKIP)
            test.log(Status.SKIP, result.getThrowable().getMessage());
        else
            test.log(Status.PASS, result.getName() + " passed");
    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
        extent.flush();
    }
}