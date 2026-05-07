package Utils;

import java.time.Duration;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {

    // WebDriver reference
    WebDriver driver;

    // Method to apply an explicit wait
    public void waitExplicitly(WebDriver driver) {

        // Assign driver to class variable
        this.driver = driver;

        // Create explicit wait with 3 seconds timeout
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        try {
            // Wait for a condition (intentional timeout)
            wait.until((WebDriver d) -> {
                return false;
            });
        }
        catch (TimeoutException e) {
            // Do nothing
        }
    }
}
