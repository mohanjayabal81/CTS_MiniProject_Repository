package configReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ObjectReader {
	
	private Properties property;

	public ObjectReader() throws IOException {
		
		// Create a Properties object to store key-value pairs from the properties file
		property=new Properties();
		
		// Create a FileInputStream to read the object repository properties file
		String projectPath = System.getProperty("user.dir");

		FileInputStream fis =
		    new FileInputStream(projectPath +
		        "\\src\\main\\java\\objectRepository\\object.properties");

		property.load(fis);
		
		fis.close();
		
	}
	
	// returns application URL
	public String getApplicationUrl() {
		return property.getProperty("baseurl");
	}	
	
	// returns search item name
	public String getSearchItemName() {
		return property.getProperty("searchItemName");
	}
	
	// returns homePage title
	public String getHomePageTitle() {
		return property.getProperty("homePageTitle");
	}
	
	// returns products page title
	public String getProductsPageTitle() {
		return property.getProperty("productsPageTitle");
	}
	
	// returns maximum price
	public String getMaxPrice() {
		return property.getProperty("maxPrice");
	}
	
	// returns minimum price
	public String getMinPrice() {
		return property.getProperty("minPrice");
	}


}
