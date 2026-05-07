package configReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ObjectReader {
	
	Properties pro;
	
	public ObjectReader(){
		try {
			pro=new Properties();
			
			// Create a file input stream to read the properties file from the project structure
			FileInputStream fis=
					new FileInputStream(System.getProperty("user.dir")
							+ "\\src\\main\\resources\\objectRepository\\object.properties");
			
			// Load the properties from the file stream into the Properties object
			pro.load(fis);	
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// Returns the base application URL from the properties file
	public String get_BaseUrl() {
		return pro.getProperty("BaseUrl");
	}

	// Returns the XPath for the search box container
	public String get_SearchBox() {
		return pro.getProperty("searchBox");
	}

	// Returns the XPath for the search input text field
	public String get_inputText() {
		return pro.getProperty("inputText");
	}

	// Returns the XPath for the auto-suggested 'mobiles under 15000' option
	public String get_MobileUnder15000Xpath() {
		return pro.getProperty("MobileUnder15000Xpath");
	}

	// Returns the XPath to verify the search text displayed on the results page
	public String get_searchTextVerify() {
		return pro.getProperty("searchTextVerify");
	}

	// Returns the XPath to display the category filters for Mobiles
	public String get_TodisplayFilters() {
		return pro.getProperty("TodisplayFilters");
	}

	// Returns the XPath for the price filter section used for scrolling
	public String scroll_toMaxElement() {
		return pro.getProperty("priceMaxFilter");
	}

	// Returns the XPath for the maximum price dropdown element
	public String get_MaxElement() {
		return pro.getProperty("maxElement");
	}

	// Returns the XPath used to scroll the page to the Operating System section
	public String get_scrollToOperatingSystem() {
		return pro.getProperty("scrollToOperatingSystem");
	}

	// Returns the XPath to select the Operating System Version dropdown
	public String get_selectOsVersion() {
		return pro.getProperty("selectOsVersion");
	}

	// Returns the XPath for the link that makes more OS versions visible
	public String get_TovisibleMoreVersions() {
		return pro.getProperty("TovisibleMoreVersions");
	}

	// Returns the XPath used to scroll specifically to find the Pie version
	public String get_scrollToMakePieVisible() {
		return pro.getProperty("scrollToMakePieVisible");
	}

	// Returns the XPath for the Android 'Pie' version checkbox
	public String get_versionPie() {
		return pro.getProperty("versionPie");
	}

	// Returns the XPath for the 'Newest First' sorting option
	public String get_newestArrivalPath() {
		return pro.getProperty("newestArrivalPath");
	}

	// Returns the XPath for the name of the first mobile in the results
	public String get_firstMobileName() {
		return pro.getProperty("firstMobileName");
	}

	// Returns the XPath for the price of the first mobile in the results
	public String get_firstMobilePrice() {
		return pro.getProperty("firstMobilePrice");
	}

	
	/***
	// Returns the XPath for the name of the second mobile in the results
	public String get_secondMobileName() {
		return pro.getProperty("secondMobileName");
	}

	// Returns the XPath for the price of the second mobile in the results
	public String get_secondMobilePrice() {
		return pro.getProperty("secondMobilePrice");
	}
		
	// Returns the XPath for the name of the third mobile in the results
	public String get_thirdMobileName() {
		return pro.getProperty("thirdMobileName");
	}

	// Returns the XPath for the price of the third mobile in the results
	public String get_thirdMobilePrice() {
		return pro.getProperty("thirdMobilePrice");
	}

	// Returns the XPath for the name of the fourth mobile in the results
	public String get_fourthMobileName() {
		return pro.getProperty("fourthMobileName");
	}

	// Returns the XPath for the price of the fourth mobile in the results
	public String get_fourthMobilePrice() {
		return pro.getProperty("fourthMobilePrice");
	}

	// Returns the XPath for the name of the fifth mobile in the results
	public String get_fifthMobileName() {
		return pro.getProperty("fifthMobileName");
	}

	// Returns the XPath for the price of the fifth mobile in the results
	public String get_fifthMobilePrice() {
		return pro.getProperty("fifthMobilePrice");
	}
	***/
	
	
	// Returns the XPath used to fetch all mobile names as a list
	public String get_AllMobileNames() {
		return pro.getProperty("getAllMobileNames");
	}

	// Returns the XPath used to fetch all mobile prices as a list
	public String get_AllMobilePrices() {
		return pro.getProperty("getAllMobilePrices");
	}

	// Returns the file path where screenshots should be saved
	public String getScreenShotProperty() {
		return System.getProperty("user.dir")+"\\"+ pro.getProperty("screenshotLocation")+"\\";
	}

	// Returns the text value expected for search verification
	public String get_expected_search_Verify_Text() {
		return pro.getProperty("expected_search_Verify_Text");
	}

	// Returns the expected URL for navigation validation
	public String getExpectedUrl() {
		return pro.getProperty("expectedUrl");
	}
}