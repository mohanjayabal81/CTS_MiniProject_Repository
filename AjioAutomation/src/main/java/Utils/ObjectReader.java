package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ObjectReader {
	FileInputStream fis;
	Properties pro;
	public ObjectReader() throws IOException {
		// Finds the current project folder dynamically
	    String projectPath = System.getProperty("user.dir");
	    
	    // Combine project path with the relative path to your file
	    String filePath = projectPath + "\\src\\main\\resources\\objectRepository\\objectProperties";
		fis = new FileInputStream(filePath);
		pro = new Properties(); 
		pro.load(fis);
	}
	public String getBaseUrl() {
		return pro.getProperty("baseUrl");
	}
	
	public String getCurTitle() {
		return pro.getProperty("title");
	}
	
	public String getSearchLocation() {
		return pro.getProperty("searchXpath");
	}

	public String getSearchItem() {
		return pro.getProperty("searchProduct");
	}
	
	public String getSelectItem() {
		return pro.getProperty("itemXPath");
	}
	
	public String getPriceOfItem() {
		return pro.getProperty("priceXpath");
	}
	
	public String getAddButton() {
		return pro.getProperty("addtocart");
	}
	
	public String getGoButton() {
		return pro.getProperty("gotocart");
	}
	
	public String getFinalPrice() {
		return pro.getProperty("finalPriceXpath");
	}

}
