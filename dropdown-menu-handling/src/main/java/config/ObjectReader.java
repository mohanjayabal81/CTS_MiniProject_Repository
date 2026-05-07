package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ObjectReader {
	// Properties object to read data from properties file
	Properties properties;
	
	// FileInputStream to read the file
	private FileInputStream fileinputstream = null;
	
	// Constructor to load object.properties file
	public ObjectReader() throws IOException {
		
		// Get current project directory path
		String objectFileLocation = System.getProperty("user.dir");
//		System.out.println(objectFileLocation);
		
		// create the full path using project directory
		String filePath = objectFileLocation + "\\Object Repository\\object.properties";
		
		// Provide full path of object.properties file
		fileinputstream = new FileInputStream(filePath);
		
		// Create Properties object
		properties = new Properties();
		
		// Load properties file data
		properties.load(fileinputstream);
		
		// Close file
		fileinputstream.close();
	}
	
	
	// Get application base URL
	public String getBaseUrl() {
		return properties.getProperty("baseURL");
	}
	
	// Get user name
	public String getName() {
		return properties.getProperty("name");
	}
	
	// Get email ID
	public String getEmail() {
		return properties.getProperty("email");
	}
	
	// Get password
	public String getPassword() {
		return properties.getProperty("password");
	}
	
	// Get day value for DOB
	public String getDay() {
		return properties.getProperty("dob.day");
	}
	
	// Get month value for DOB
	public String getMonth() {
		return properties.getProperty("dob.month");
	}
	
	// Get year value for DOB
	public String getYear() {
		return properties.getProperty("dob.year");
	}
	
	// Get browser name
	public String getBrowser() {
		return properties.getProperty("browser");
	}
	
	// Select city name
	public String getCity() {
		return properties.getProperty("city");
	}
}
