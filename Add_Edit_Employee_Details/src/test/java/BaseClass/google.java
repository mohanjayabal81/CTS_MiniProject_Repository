package BaseClass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class google {
	public static void main(String args[]) {
		String str="Aravind";
		int count=0;
//		System.out.println(str.length());
		for(int i=0;i<str.length();i++) {
			count++;
		}
		System.out.println(count);
	}
}
