package BrowserImplimentation;
import java.util.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class BrowserConfiguration {
	private WebDriver driver;
	
	public WebDriver chooseBrowser() {
		
		driver = new ChromeDriver();
//		Scanner sc=new Scanner(System.in);
//		System.out.println("Select a Browser to Launch: ");
//        System.out.println("1.Chrome");
//        System.out.println("2.Edge");
//        System.out.print("Enter your choice : ");
//        String choice=sc.nextLine();
//        sc.close();
//        switch(choice) {
//        case "1":
//        	driver =new ChromeDriver();
//        	break;
//        case "2":
//        	driver=new EdgeDriver();
//        	break;
//        default:
//            throw new RuntimeException("Invalid choice");            
//        }
        driver.manage().window().maximize();
        return driver;
	}
	
	
	public void closeBrwowser() {
		driver.close();
	}
}
