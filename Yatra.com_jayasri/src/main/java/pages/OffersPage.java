
package pages;

import java.time.Duration;
import java.util.Set;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import utils.ObjectReader;

/*
 * Page Object class for Offers Page
 */
public class OffersPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private ObjectReader u;

    public OffersPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.u = new ObjectReader();
    }

    /*
     * Switches control from parent window
     * to newly opened Offers tab
     */
    public void switchToOffersTab() {

        String parentWindow = driver.getWindowHandle();

        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }

    /*
     * Returns banner heading text
     */
    public String getBannerText() {
        By banner = By.xpath(u.getLocator("bannerHeading"));
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(banner))
                .getText();
    }
}