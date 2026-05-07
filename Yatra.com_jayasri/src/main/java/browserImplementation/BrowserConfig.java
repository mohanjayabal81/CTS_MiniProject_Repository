
package browserImplementation;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/*
 * This class is responsible for launching the browser.
 * It allows user to choose Chrome or Edge at runtime.
 */
public class BrowserConfig {

    // WebDriver reference accessible across methods
    private WebDriver driver;

    /*
     * Prompts user to select browser
     * Returns initialized WebDriver instance
     */
    public WebDriver chooseBrowser() {

        Scanner sc = new Scanner(System.in);

//        System.out.println("1. Chrome");
//        System.out.println("2. Edge");
//        System.out.print("Choose Browser: ");
//        String choice = sc.nextLine();
//
//        switch (choice) {
//
//            case "1":
//                // Disable browser notifications
//                ChromeOptions options = new ChromeOptions();
//                options.addArguments("--disable-notifications");
//                driver = new ChromeDriver(options);
//                break;
//
//            case "2":
//                driver = new EdgeDriver();
//                break;
//
//            default:
//                // Default browser = Chrome
//                ChromeOptions defaultOptions = new ChromeOptions();
//                defaultOptions.addArguments("--disable-notifications");
//                driver = new ChromeDriver(defaultOptions);
//                break;
//        }
        driver = new ChromeDriver();
        sc.close();

        // Maximize browser window
        driver.manage().window().maximize();

        return driver;
    }

    /*
     * Safely closes browser and ends session
     */
    public void closeBrowser() {
            driver.quit();
    }
}