package utils;

import java.io.IOException;
import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverManager {

    // Reads application configuration values
    private PropertiesReader propertiesReader;

    // Shared WebDriver instance
    protected WebDriver driver;

    // Constructor handles browser initialization and application launch
    public DriverManager() throws IOException {

        // Take browser choice from user at runtime
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Select a Browser to Launch: ");
//        System.out.println("1.Chrome");
//        System.out.println("2.Edge");
//        System.out.print("Enter your choice : ");
//
//        String choice = sc.nextLine();
//        sc.close();
//
//        // Launch browser based on user selection
//        switch (choice) {
//            case "1":
//                driver = new ChromeDriver();
//                break;
//            case "2":
//                driver = new EdgeDriver();
//                break;
//            default:
//                throw new RuntimeException("Invalid choice");
//        }
    	
    	driver = new ChromeDriver();

        // Maximize browser window
        driver.manage().window().maximize();

        // Load application URL from properties file
        propertiesReader = new PropertiesReader();
        driver.get(propertiesReader.getProperty("URL"));
    }

    // Returns active WebDriver instance
    public WebDriver getWebDriver() {
        return driver;
    }

    // Closes browser and releases resources
    public void killDriver() throws IOException {
        driver.quit();
        propertiesReader.killFileInputStream();
    }
}
