package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Browsers {
    WebDriver driver;

//    launching browser chrome or edge
    public WebDriver getDriver(String browserName){
        if(browserName.equalsIgnoreCase("chrome")){
            driver=new ChromeDriver();
            return driver;
        } else if (browserName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
            return driver;
        }else {
            throw new IllegalArgumentException("Unsupported browser: "+browserName);
        }
    }
}
