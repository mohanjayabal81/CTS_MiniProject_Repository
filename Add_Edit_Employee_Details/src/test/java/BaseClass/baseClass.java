package BaseClass;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
//import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import BrowserImplimentation.BrowserConfiguration;


import TestingPages.LogOut;
import TestingPages.addDetails;
import TestingPages.editDetails;
import TestingPages.login;
public class baseClass {
	private WebDriver driver;
	login Login;
	addDetails add;
	editDetails edit;
	LogOut logout;
	BrowserConfiguration bc;
	@BeforeSuite
	public void BrowserChoose() {
		bc=new BrowserConfiguration();
		driver=bc.chooseBrowser();
	}
	@Test
	public void LoginPage()  {
		Login=new login(driver);
		try {
			Login.loginPage(driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
		System.out.println("Login");
		}
	}
	
	@Test(dependsOnMethods="LoginPage")
	public void AddDetails_Page() {
		add=new addDetails(driver);
		try {
			add.add(driver);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("AddDetails");
	}
	@Test(dependsOnMethods="AddDetails_Page")
	public void EditDetails_Page()  {
		edit=new editDetails(driver);
		try {
			edit.Edit(driver);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
		System.out.println("Edit");
		}
	}

	@Test(dependsOnMethods="EditDetails_Page")
	public void Logout_Page() throws InterruptedException{
		logout=new LogOut(driver);
		try {
			logout.out(driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
		System.out.println("Logout");
		}
	}
@AfterSuite
public void closeBrowser() {
	bc.closeBrwowser();
}

}
