package browsers;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class BrowserImplementation {

	WebDriver driver;
	
	public WebDriver getBrowser(String browserName) {
		//Chrome Browser
		if(browserName.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("edge")){
			//Edge Browser
			driver=new EdgeDriver();
		}
		driver.manage().window().maximize();
		return driver;
	}
}
