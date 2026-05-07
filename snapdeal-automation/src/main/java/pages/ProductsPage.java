package pages;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import configReader.ObjectReader;
import utils.Utilities;

public class ProductsPage {
	
	private WebDriver driver;
	private ObjectReader objectReader;
	private Utilities utilities;
	
	By minPriceInput = By.name("fromVal");
	By maxPriceInput = By.name("toVal");
	By goButtonInput = By.xpath("//*[@id=\"content_wrapper\"]/div[7]/div[3]/div/div[1]/div[2]/div[2]/div[3]/div/div[5]");
	By sortOptionInput = By.className("sort-selected");
	By popularityOptionInput = By.xpath("//*[@id=\"content_wrapper\"]/div[7]/div[4]/div[3]/div[1]/div/div[2]/ul/li[2]");
	By productNamesInput = By.cssSelector("p.product-title");
	By productPricesInput = By.cssSelector("span.product-price");
	
	// products page constructor to initialize objects
	public ProductsPage(WebDriver driver) throws IOException {
		objectReader=new ObjectReader();
		this.driver=driver;
		utilities = new Utilities();
	}
	
	
	// returning current products page title
	public String getProductsPageTitle() {
		String actualTitle = driver.getTitle();
		return actualTitle;
	}
	
	public void setPriceRange() {
		//setting minimum price value
		WebElement minPrice=driver.findElement(minPriceInput);
		minPrice.clear();
		minPrice.sendKeys(objectReader.getMinPrice());
		
		//setting maximum price value
		WebElement maxPrice=driver.findElement(maxPriceInput);
		maxPrice.clear();
		maxPrice.sendKeys(objectReader.getMaxPrice());
		
		//finding and clicking Go button 
		WebElement goButton = driver.findElement(goButtonInput);
		goButton.click();
		
	}
	public void sortByPopularity()  {
		
		// Initializes WebDriverWait with a maximum timeout of 10 seconds
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		
		//clicking sort by option
		WebElement sortOption = wait.until(
				ExpectedConditions.elementToBeClickable(sortOptionInput));
		sortOption.click();

		//selecting popularity option
		WebElement popularityOption = wait.until(
				ExpectedConditions.elementToBeClickable(popularityOptionInput));
		popularityOption.click();
		
	}
	
	public void fetchAndPrintProductNamesAndPrices()  {
		
		// pause program for some seconds
		utilities.waitExplicitly(driver);

		//finding products names and prices
		List<WebElement> productsNames = driver.findElements(productNamesInput);
		List<WebElement> productPrices = driver.findElements(productPricesInput);
				
		//printing 5 prices and names of products
		int min=Math.min(productsNames.size(),5);
		for(int i=0;i<min;i++) {
			String productName= productsNames.get(i).getText();
			String productPrice = productPrices.get(i).getText();

			System.out.println("ProductName-"+(i+1)+" :- "+productName);
			System.out.println("ProductPrice-"+(i+1)+" :- "+productPrice);
			System.out.println("------------------------->");
		}
	}
	

}
