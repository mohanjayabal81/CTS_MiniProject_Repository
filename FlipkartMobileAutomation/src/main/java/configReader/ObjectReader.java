package configReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class ObjectReader {

    // Properties object to hold all key-value pairs from the file
    private final Properties properties;

    // Path to the object repository properties file
    private final String PROPERTIES_FILE_PATH =
            "src/main/resources/objectRepository/object.properties";

   
    public ObjectReader() throws IOException {
        properties = new Properties();

        // try-with-resources ensures the stream is closed after loading implicit closing
        try (FileInputStream fileInputStream = new FileInputStream(PROPERTIES_FILE_PATH)) {
            properties.load(fileInputStream);
            System.out.println("[INFO] Object repository loaded successfully.");
        }
    }


    // Returns the base URL of the application under test 
    public String getBaseUrl() {
        return properties.getProperty("baseUrl");
    }

    // Search Page Locators

    //Returns XPath of the main search input box 
    public String getSearchBoxXpath() {
        return properties.getProperty("searchBox");
    }

    // Returns XPath of the "mobiles under 15000" auto-suggestion option 
    public String getMobilesUnder15000Xpath() {
        return properties.getProperty("mobilesUnder15000");
    }

    //Returns XPath of the search result label used for verification 
    public String getSearchResultVerifyXpath() {
        return properties.getProperty("searchResultVerify");
    }

    // Home Page Locators

    //Returns XPath of the popup close button on the Flipkart home page 
    public String getPopupCloseButtonXpath() {
        return properties.getProperty("popupCloseButton");
    }

    // Filter Section Locators

    // Returns XPath to click and display the Filters panel 
    public String getDisplayFiltersXpath() {
        return properties.getProperty("displayFilters");
    }

    // Returns XPath of the price range section (used to scroll into view) 
    public String getPriceRangeSectionXpath() {
        return properties.getProperty("priceRangeSection");
    }

    //Returns XPath of the max price dropdown (₹10,000 option) 
    public String getMaxPriceDropdownXpath() {
        return properties.getProperty("maxPriceDropdown");
    }

    //Returns XPath to scroll the Operating System filter section into view 
    public String getOperatingSystemSectionXpath() {
        return properties.getProperty("operatingSystemSection");
    }

    //Returns XPath of the "Operating System Version Name" filter heading 
    public String getOsVersionHeadingXpath() {
        return properties.getProperty("osVersionHeading");
    }

    //Returns XPath of the "More" button to expand hidden OS version options 
    public String getShowMoreOsVersionsXpath() {
        return properties.getProperty("showMoreOsVersions");
    }

    // Returns XPath of a reference element to scroll "Pie" version into view 
    public String getScrollToPieReferenceXpath() {
        return properties.getProperty("scrollToPieReference");
    }

    //Returns XPath of the "Pie" OS version checkbox/filter option 
    public String getPieVersionXpath() {
        return properties.getProperty("pieVersion");
    }

    // ---------------------------------------------------------------
    // Sort Locators
    // ---------------------------------------------------------------

    /** Returns XPath of the "Newest First" sort option */
    public String getNewestFirstXpath() {
        return properties.getProperty("newestFirst");
    }

    // Mobile Listing Locators (Name + Price for positions 1–5)

    //Returns XPath of the 1st mobile's name in the listing 
    public String getFirstMobileNameXpath() {
        return properties.getProperty("firstMobileName");
    }

    // Returns XPath of the 1st mobile's price in the listing 
    public String getFirstMobilePriceXpath() {
        return properties.getProperty("firstMobilePrice");
    }

    //Returns XPath of the 2nd mobile's name in the listing 
    public String getSecondMobileNameXpath() {
        return properties.getProperty("secondMobileName");
    }

    // Returns XPath of the 2nd mobile's price in the listing 
    public String getSecondMobilePriceXpath() {
        return properties.getProperty("secondMobilePrice");
    }

    //Returns XPath of the 3rd mobile's name in the listing 
    public String getThirdMobileNameXpath() {
        return properties.getProperty("thirdMobileName");
    }

    // Returns XPath of the 3rd mobile's price in the listing 
    public String getThirdMobilePriceXpath() {
        return properties.getProperty("thirdMobilePrice");
    }

    // Returns XPath of the 4th mobile's name in the listing 
    public String getFourthMobileNameXpath() {
        return properties.getProperty("fourthMobileName");
    }

    // Returns XPath of the 4th mobile's price in the listing 
    public String getFourthMobilePriceXpath() {
        return properties.getProperty("fourthMobilePrice");
    }

    //Returns XPath of the 5th mobile's name in the listing 
    public String getFifthMobileNameXpath() {
        return properties.getProperty("fifthMobileName");
    }

    //Returns XPath of the 5th mobile's price in the listing 
    public String getFifthMobilePriceXpath() {
        return properties.getProperty("fifthMobilePrice");
    }
}