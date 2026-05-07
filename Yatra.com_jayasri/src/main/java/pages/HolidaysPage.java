
package pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import utils.ObjectReader;

/*
 * Page Object class for Holidays Page
 */
public class HolidaysPage {

    private WebDriverWait wait;
    private ObjectReader u;

    public HolidaysPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.u = new ObjectReader();
    }

    /*
     * Clicks Holidays link
     * Returns list of holiday packages
     */
    public List<WebElement> getHolidayPackages() {

        wait.until(ExpectedConditions
                .elementToBeClickable(By.linkText(u.getLocator("holidaysLink"))))
                .click();

        return wait.until(ExpectedConditions
                .presenceOfAllElementsLocatedBy(
                        By.cssSelector(u.getLocator("packageTitles"))));
    }
}