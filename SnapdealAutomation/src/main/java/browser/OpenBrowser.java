package browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
 
public class OpenBrowser {

    // Opens Chrome browser
    public static WebDriver chromeBrowser() {
        WebDriver driver = new ChromeDriver();   // create Chrome driver
        driver.manage().window().maximize();     // maximize window

        // open Snapdeal website
        driver.get("https://www.snapdeal.com");

        return driver;  // return driver object
    }

    // Opens Edge browser
    public static WebDriver edgeBrowser() {
        WebDriver driver = new EdgeDriver();      // create Edge driver
        driver.manage().window().maximize();     // maximize window

        // open Snapdeal website
        driver.get("https://www.snapdeal.com");

        return driver;  // return driver object
    }
}
