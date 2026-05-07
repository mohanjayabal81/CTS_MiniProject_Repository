package UTILS;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ObjectReader {

    private Properties pro;

    public Properties getPro() {
        return pro;
    }

    public void setPro(Properties pro) {
        this.pro = pro;
    }

    // Initializes the Properties object and loads the Object.properties file
    // using a FileInputStream so all locators and config values are available
    public ObjectReader() throws IOException {
        pro = new Properties();
        FileInputStream fis = new FileInputStream(
            "C:\\Users\\2479744\\eclipse-workspace\\com.Selenium.Project.AmazonTest" +
            "\\src\\main\\resources\\OBJECTREPOSITORY\\Object.properties"
        );
        pro.load(fis);
    }


    // Returns the base URL of the Amazon India website
    public String getBaseUrl() {
        return pro.getProperty("BaseUrl");
    }

    // Returns the expected browser title of the Amazon homepage
    // Used for page load validation
    public String getHomeTitle() {
        return pro.getProperty("HomePageTitle");
    }


    // Returns the ID of the search input text box
    public String getSearchObject() {
        return pro.getProperty("Amazon.Search.id");
    }

    // Returns the ID of the search submit button
    public String getSearchButtonObject() {
        return pro.getProperty("Amazon.SearchButton.id");
    }

    // Returns the search keyword to be typed into the search box
    public String getSearchKeyValue() {
        return pro.getProperty("Amazon.Search.Key");
    }



    // Returns the XPath of the "Results" heading on the search results page
    public String getSearchTextObject() {
        return pro.getProperty("Amazon.SearchPage.ResultText.xpath");
    }

    // Returns the expected text of the "Results" heading for assertion
    public String getSearchPageTextVerifyProperty() {
        return pro.getProperty("Amazon.SearchPage.ResultText");
    }

    // Returns the XPath to retrieve the current value inside the search box
    // on the results page (verifies the search term was retained after navigation)
    public String getSearchBoxValueObject() {
        return pro.getProperty("Amazon.SearchPage.SearchBoxValue.xpath");
    }

    // Returns the XPath of the result count/description text element
    // e.g., "1-20 of 1000 results for "mobile smartphones under 30000""
    public String getSearchPageValidateTextObject() {
        return pro.getProperty("Amazon.SearchPage.validateText.xpath");
    }

    // Returns the regex pattern used to validate the result count text format
    // Capture groups: (start index)-(end index) of (total results) for "%s" (search keyword)
    public String getSearchPageValidateTextProperty() {
        return pro.getProperty("Amazon.SearchPage.validateText.regex");
    }


    // Returns the XPath to locate and click the "Sort by:" dropdown button
    public String getSortListObject() {
        return pro.getProperty("Amazon.SearchPage.Sortlist.xpath");
    }

    // Returns the ID of the "Newest Arrivals" option in the Sort By dropdown
    public String getSortListSelectObject() {
        return pro.getProperty("Amazon.Searchpage.Sortlist.newestArrival.id");
    }

    // Returns the XPath to verify the currently selected sort option in the dropdown
    public String getSortListResultObject() {
        return pro.getProperty("Amazon.SearchPage.Sortlist.resultVerify.xpath");
    }

    // Returns the expected text of the selected sort option after choosing "Newest Arrivals"
    public String getSortListResultProperty() {
        return pro.getProperty("Amazon.SearchPage.Sortlist.resultVerify.text");
    }


    // Returns the XPath to locate all product cards on the search results page
    // Uses 'data-component-type' attribute to identify each search result container
    public String getSearchResultObject() {
        return pro.getProperty("Amazon.SearchPage.SearchResult.xpath");
    }

    // Returns the relative XPath (within a result card) to detect sponsored listings
    // Used to skip sponsored items when collecting top organic results
    public String getSponsoredLabelObject() {
        return pro.getProperty("Amazon.SearchPage.SponsoredLabel.xpath");
    }

    // Returns the relative XPath (within a result card) to extract the product name
    // Targets the specific h2 class used for standard (non-sponsored) product titles
    public String getProductNameObject() {
        return pro.getProperty("Amazon.SearchPage.ProductName.xpath");
    }

    // Returns the relative XPath (within a result card) to extract the product price
    // Extracts the whole number part of the price, e.g., "24,999" from "₹24,999"
    public String getProductPriceObject() {
        return pro.getProperty("Amazon.SearchPage.ProductPrice.xpath");
    }

    // Returns the currency symbol prefix to prepend to the extracted price value
    // e.g., "₹" — combined with extracted price to form the full display price
    public String getProductPricePrefix() {
        return pro.getProperty("Amazon.SearchPage.ProductPrice.prefix");
    }

    // Returns the fallback text to display when a product has no listed price
    public String getProductPriceFallback() {
        return pro.getProperty("Amazon.SearchPage.ProductPrice.fallback");
    }

    // Returns the maximum number of top products to collect from search results
    // Parsed as an integer since it is used as a numeric limit in loop conditions
    public int getTopProductsCount() {
        return Integer.parseInt(pro.getProperty("Amazon.SearchPage.TopProducts.count"));
    }


    // Returns the absolute file system path where test screenshots will be saved
    public String getScreenShotProperty() {
        return pro.getProperty("Amazon.ScreenShot.path");
    }
}