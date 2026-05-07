package listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import Utils.ScreenshotUtil;
import testcases.SnapdealTest;

public class TestListener implements ITestListener {

    // This method runs when a test case passes
    @Override
    public void onTestSuccess(ITestResult result) {

        // Get the current test class instance
        Object testClass = result.getInstance();

        // Get WebDriver from test class
        WebDriver driver = ((SnapdealTest) testClass).getDriver();

        // Take screenshot for passed test case
        ScreenshotUtil.takeScreenshot(driver, result.getName());
    }
}