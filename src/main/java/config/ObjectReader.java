package config;

import java.io.FileInputStream;
import java.util.Properties;

public class ObjectReader {
	
	Properties prop;
	
	public ObjectReader() {
		try {
			String projectPath = System.getProperty("user.dir");
			String objectRepoPath = projectPath + "\\ObjectRepository\\object.properties";
			
			FileInputStream fis=new FileInputStream(objectRepoPath);
			prop=new Properties();
			prop.load(fis);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	public String getBaseUrl() {
		return prop.getProperty("baseUrl");
	}
	
	public String getBrowserRead() {
		return prop.getProperty("browser");
	}
	
	public String getLandingCheck() {
		return prop.getProperty("landingPageTitle");
	}
	
	public String getLoginAlert() {
		return prop.getProperty("loginAlert");
	}
	
	public String getPasswordAlert() {
		return prop.getProperty("forgotAlert");
	}
	
	public String getTitlePrivacyPage() {
		return prop.getProperty("titlePrivacyPage");
	}

}
