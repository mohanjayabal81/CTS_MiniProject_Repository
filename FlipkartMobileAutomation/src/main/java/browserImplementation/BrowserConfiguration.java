package browserImplementation;

import java.util.Scanner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class BrowserConfiguration {

    // WebDriver instance shared across the class
    private WebDriver driver;


    public WebDriver launchBrowser() {

        // Ask the user to choose a browser at runtime
        Scanner scanner = new Scanner(System.in);
        System.out.println("  Select Browser to Launch:");
        System.out.println("  1 -> Google Chrome");
        System.out.println("  2 -> Mozilla Firefox");
        System.out.println("  3 -> Microsoft Edge");
        System.out.print("  Enter your choice (1 / 2 / 3): ");

        String userChoice = scanner.nextLine().trim();
        scanner.close();

        // Launch browser based on user's choice
        switch (userChoice) {

            case "1":
                // Launch Google Chrome
                System.out.println(" Launching Google Chrome...");
                driver = new ChromeDriver();
                break;

            case "2":
                // Launch Mozilla Firefox
                System.out.println(" Launching Mozilla Firefox...");
                driver = new FirefoxDriver();
                break;

            case "3":
                // Launch Microsoft Edge
                System.out.println(" Launching Microsoft Edge...");
                driver = new EdgeDriver();
                break;

            default:
                // Invalid input — fall back to Chrome
                System.out.println(" Invalid choice entered. Defaulting to Google Chrome...");
                driver = new ChromeDriver();
                break;
        }

        return driver;
    }
}