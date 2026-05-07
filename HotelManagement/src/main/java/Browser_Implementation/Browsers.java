package Browser_Implementation;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

// Factory class to launch a browser based on user input
public class Browsers {
     private WebDriver driver;
    // Returns a browser instance based on user choice: 1 = Chrome, 2 = Edge
    public WebDriver getBrowser() {
    	
    	    System.out.println("Choose the Browser ");
    	    System.out.println("1.Chrome");
    	    System.out.println("2.Edge");
        Scanner scanner=new Scanner(System.in);
        int choice=scanner.nextInt();
        switch (choice) {
            case 1:
                // Launch Chrome browser
                driver = new ChromeDriver();
                System.out.println("Chrome browser launched successfully");
                break;
            case 2:
                // Launch Edge browser
                driver = new EdgeDriver();
                System.out.println("Edge browser launched successfully");
                break;
            default:
                // Handle invalid input
                System.out.println("Invalid choice! Please enter 1 for Chrome or 2 for Edge");
                break;
        }
        return driver;
    }
}