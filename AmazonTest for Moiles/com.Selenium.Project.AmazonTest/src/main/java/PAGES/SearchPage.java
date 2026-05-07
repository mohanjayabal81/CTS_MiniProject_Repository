package PAGES;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import UTILS.ObjectReader;

public class SearchPage {

    private WebDriver driver;
    private ObjectReader or;

    // Initializes the WebDriver and ObjectReader instances
    // ObjectReader loads all locators and config values from the properties file
    public SearchPage(WebDriver driver) throws IOException {
        this.driver = driver;
        or = new ObjectReader();
    }


    // -------------------------------------------------------------------------
    // Search Results Page - Heading Validation
    // -------------------------------------------------------------------------

    // Finds and returns the "Results" heading WebElement on the search results page
    // XPath sourced from: Amazon.SearchPage.ResultText.xpath
    public WebElement getActualSearchPageText() {
        WebElement text = driver.findElement(By.xpath(or.getSearchTextObject()));
        return text;
    }

    // Returns the expected "Results" heading text for assertion comparison
    // Value sourced from: Amazon.SearchPage.ResultText
    public String getExpectedSearchPageText() {
        return or.getSearchPageTextVerifyProperty();
    }


    // Retrieves the current value typed/retained in the search box on the results page
    // Verifies the search keyword was preserved after navigating to results
    // XPath sourced from: Amazon.SearchPage.SearchBoxValue.xpath
    public String getSearchBoxValueO() {
        WebElement text = driver.findElement(By.xpath(or.getSearchBoxValueObject()));
        return text.getAttribute("value");
    }



    // Finds and returns the result count description text from the results page
    // e.g., "1-20 of 1000 results for "mobile smartphones under 30000""
    // XPath sourced from: Amazon.SearchPage.validateText.xpath
    public String getActualSearchPageValidateText() {
        WebElement text = driver.findElement(By.xpath(or.getSearchPageValidateTextObject()));
        return text.getText();
    }

    // Builds and returns the expected result count text using a regex/format pattern
    // %s in the pattern is replaced with the actual search keyword passed as 'value'
    // Pattern sourced from: Amazon.SearchPage.validateText.regex
    public String getExpectedSearchPageValidateText(String value) {
        String s = String.format(or.getSearchPageValidateTextProperty(), value);
        return s;
    }



    // Finds and returns the "Sort by:" dropdown button WebElement
    // XPath sourced from: Amazon.SearchPage.Sortlist.xpath
    public WebElement getSortlist() {
        WebElement clickable = driver.findElement(By.xpath(or.getSortListObject()));
        return clickable;
    }

    // Finds and returns the "Newest Arrivals" option WebElement inside the sort dropdown
    // ID sourced from: Amazon.Searchpage.Sortlist.newestArrival.id
    public WebElement getSortlistNewestArrival() {
        WebElement ele = driver.findElement(By.id(or.getSortListSelectObject()));
        return ele;
    }

    // Returns the currently selected sort option text displayed in the dropdown
    // Used to verify "Newest Arrivals" was selected successfully
    // XPath sourced from: Amazon.SearchPage.Sortlist.resultVerify.xpath
    public String getActualSortlistResult() {
        String res = driver.findElement(By.xpath(or.getSortListResultObject())).getText();
        return res;
    }

    // Returns the expected sort option text for assertion comparison
    // Value sourced from: Amazon.SearchPage.Sortlist.resultVerify.text
    public String getExpectedSortListResult() {
        return or.getSortListResultProperty();
    }




    // Collects the top N non-sponsored products and their prices from the search results page
    // N is driven by the properties file (Amazon.SearchPage.TopProducts.count)
    // Returns a Map of { productName -> price }
    public Map<String, String> getTop5Products() {

        // Wait up to 10 seconds for all product cards to be present in the DOM
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
            By.xpath(or.getSearchResultObject())
        ));

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Fetch all product card elements using the container XPath from properties
        List<WebElement> items = driver.findElements(
            By.xpath(or.getSearchResultObject())
        );

        System.out.println("Total items found: " + items.size());

        Map<String, String> topProducts = new LinkedHashMap<>();

        for (WebElement item : items) {

            // Stop once we have collected the required number of products
            if (topProducts.size() >= or.getTopProductsCount()) break;

            try {
                // Scroll the product card smoothly into the visible viewport
                js.executeScript(
                    "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", item
                );

                // Wait for the product card to become visible before interacting
                wait.until(ExpectedConditions.visibilityOf(item));

                // Check if the current item is a sponsored listing using the sponsored label XPath
                // Sponsored items are skipped to collect only organic results
                boolean isSponsored = !item.findElements(
                    By.xpath(or.getSponsoredLabelObject())
                ).isEmpty();
                if (isSponsored) 
                	continue;

                // Locate the product name element within the current card using the title XPath
                List<WebElement> nameElements = item.findElements(
                    By.xpath(or.getProductNameObject())
                );

                // Skip this card if no name element was found
                if (nameElements.isEmpty()) continue;

                String name = nameElements.get(0).getText().trim();

                // Skip this card if the extracted name text is blank
                if (name.isEmpty()) continue;

                // Attempt to extract the product price from the price element
                // Falls back to the configured fallback text if no price is found
                String price;
                try {
                    price = or.getProductPricePrefix() + item.findElement(
                        By.xpath(or.getProductPriceObject())
                    ).getText().trim();
                } catch (NoSuchElementException e) {
                    price = or.getProductPriceFallback();
                }

                topProducts.put(name, price);

            } catch (Exception e) {
                // Log and skip any card that throws an unexpected exception during processing
                System.out.println("Skipped item: " + e.getMessage());
            }
        }

        return topProducts;
    }


	public WebDriver getDriver() {
		return driver;
	}


	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}


	public ObjectReader getOr() {
		return or;
	}


	public void setOr(ObjectReader or) {
		this.or = or;
	}
}