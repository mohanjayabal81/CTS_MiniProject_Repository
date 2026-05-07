package configreader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ObjectReader {
	Properties per = null;
	public ObjectReader() throws IOException{
		per = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\2478625\\eclipse-workspace\\Selenium_w\\MiniProject\\ObjectRepository\\object.properties");
		per.load(fis);
	}
	//
	public String getUrl() {
		return per.getProperty("siteUrl");
	}
	//
	public String getskillsComboBox() {
		return per.getProperty("keyword.input");
	}
	//
	public String getLocationComboBox() {
		return per.getProperty("location.input");
	}
	//
	public String getExperienceDropDown(){
		return per.getProperty("experience.dropdown.arrow");
	}
	//
	public String selectExperience(){
		return per.getProperty("experience.option");
	}
	//
	public String clickOnSearch() {
		return per.getProperty("search.button");
	}
	//
	public String getHeader(){
		return per.getProperty("results.header");
	}
	//
	public String getExperience(){
		return per.getProperty("results.experience.value");
	}
}