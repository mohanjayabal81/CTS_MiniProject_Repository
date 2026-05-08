package browser_Implementation;
import java.util.Scanner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
public class Browsers {
	WebDriver driver;
	public WebDriver select_Browser() throws Exception
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("choose browser: ");
		System.out.println("1. Chrome");
		System.out.println("2. Edge");
		String bName=sc.next().trim();
		sc.close();
		switch(bName){
			case "1" : 
				driver = new ChromeDriver(); break;
			case "2" :
				driver = new EdgeDriver(); break;
			default : throw new Exception("Invalid Browser");
		}
		driver.manage().window().maximize();
		return driver;
	}
	public void closeBrowser() {
		driver.quit();
	}
}