package TestingPages;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ReusableMethod;
import utils.objectFetch;


public class LogOut {
	private WebDriver driver;
	private objectFetch object;
	WebDriverWait wait;
	ReusableMethod rm;
	public LogOut(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		try {
			this.object=new objectFetch();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.wait=new WebDriverWait(driver,Duration.ofSeconds(30));
		this.rm=new ReusableMethod(driver);
	}

	public void out(WebDriver driver){
		try {
//			objectFetch object=new objectFetch();
//		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(30));
         wait.until(ExpectedConditions.elementToBeClickable(By.xpath(object.getLocator("profile")))).click();;
         wait.until(ExpectedConditions.elementToBeClickable(By.xpath(object.getLocator("logout"))));
		}
		catch(Exception e) {
			System.out.println("Log Out Failed "+e.getMessage());
		}
	}
}
