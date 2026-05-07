package browserImplementation;
import java.util.*;
import java.io.IOException;
 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
 
import configReader.ConfigReader;
 
public class BrowserConfig_Reader {
	private WebDriver driver;
	ConfigReader cr;

	public BrowserConfig_Reader(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public int get_Browser()
	{
		Scanner sc= new Scanner(System.in);
		System.out.println("Select browser:");
		System.out.println("1.Chrome Browser");
		System.out.println("2.Edge Browser");
		int number=sc.nextInt();
		return number;
	}
	public WebDriver get_chrome(ChromeOptions options)
	{
		 driver=new ChromeDriver(options);
		 return driver;
	}
	public WebDriver get_Edge(EdgeOptions options)
	{
		 driver=new EdgeDriver(options);
		 return driver;
	}
	public void Launch_Url()
	{
		try
		{
		cr= new ConfigReader();
		driver.get(cr.get_Url());
		}
		catch(IOException e)
		{
			System.out.println("e");
		}
	}
	public void Close_Browser()
	{
		driver.quit();
	}
 
	
 
}