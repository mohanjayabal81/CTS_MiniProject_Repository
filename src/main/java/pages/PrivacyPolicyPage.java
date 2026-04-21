package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PrivacyPolicyPage {

    WebDriver driver;

    public PrivacyPolicyPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPrivacyPolicyTitle() {

		String parentWindow = driver.getWindowHandle();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Privacy Policy")));
		js.executeScript("arguments[0].click();", element);

		//Wait for new tab to open
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));

		//Switch to new window
		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(parentWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}

		//Get title of Privacy Policy page
		String title = driver.getTitle();
		driver.close();

		//switch back to parent window
		driver.switchTo().window(parentWindow);

		return title;

	}
}
