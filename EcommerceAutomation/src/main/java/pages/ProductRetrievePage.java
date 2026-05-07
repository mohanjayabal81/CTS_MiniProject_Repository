package pages;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import configReader.ObjectReader;

public class ProductRetrievePage {
	
	WebDriver driver;
	ObjectReader or;
	//Constructor to intialize driver
	public ProductRetrievePage(WebDriver driver)  {
		this.driver=driver;
		or=new ObjectReader();
	}
	
	//To verify the price of first mobile
	public int verify_firstMobile_PriceCriteria() {
		
		
		//To get the price of first mobile
		WebElement firstMobilePrice=driver.findElement(By.xpath(or.get_firstMobilePrice()));
		String price=firstMobilePrice.getText();
		
		//regex to make the price to be only integers
		price=price.replaceAll("[^0-9]", "");
		int mp=Integer.parseInt(price);
		
		return mp;
	}
	
	
	/*
	public void mobiles_fetching_underCriteria() {
		
		//To get price and names of mobiles
		WebElement firstMobile=driver.findElement(By.xpath(or.get_firstMobileName()));
		String firstMobilename=firstMobile.getText();
		WebElement firstMobilePrice=driver.findElement(By.xpath(or.get_firstMobilePrice()));
		String mobilePrice=firstMobilePrice.getText();
		System.out.println(firstMobilename);
		System.out.println(mobilePrice);
		
		WebElement secondMobile=driver.findElement(By.xpath(or.get_secondMobileName()));
		System.out.println(secondMobile.getText());
		WebElement secondMobilePrice=driver.findElement(By.xpath(or.get_secondMobilePrice()));
		System.out.println(secondMobilePrice.getText());
		
		WebElement thirdMobile=driver.findElement(By.xpath(or.get_thirdMobileName()));
		System.out.println(thirdMobile.getText());
		WebElement thirdMobilePrice=driver.findElement(By.xpath(or.get_thirdMobilePrice()));
		System.out.println(thirdMobilePrice.getText());
		
		WebElement fourthMobile=driver.findElement(By.xpath(or.get_fourthMobileName()));
		System.out.println(fourthMobile.getText());
		WebElement fourthMobilePrice=driver.findElement(By.xpath(or.get_fourthMobilePrice()));
		System.out.println(fourthMobilePrice.getText());
		
		WebElement fifthMobile=driver.findElement(By.xpath(or.get_fifthMobileName()));
		System.out.println(fifthMobile.getText());
		WebElement fifthMobilePrice=driver.findElement(By.xpath(or.get_fifthMobilePrice()));
		System.out.println(fifthMobilePrice.getText());
	}
	*/
	
	
	public void mobiles_fetching_underCriteria() {
		
        // Fetching lists of elements
		//Getting all mobile names
        List<WebElement> mobileNames = driver.findElements(By.xpath(or.get_AllMobileNames()));
        //Getting all mobile prices
        List<WebElement> mobilePrices = driver.findElements(By.xpath(or.get_AllMobilePrices()));

        // Create Excel Workbook and Sheet
        try {
        	//create a new excel workbook
        	XSSFWorkbook workbook = new XSSFWorkbook();
        	
        	//create a new excel worksheet
            XSSFSheet sheet = workbook.createSheet("Mobiles");
            
            // Create Header Row
            XSSFRow header = sheet.createRow(0);
            
            //Create first and second columns
            header.createCell(0).setCellValue("Mobile Name");
            header.createCell(1).setCellValue("Price");

            // limit how many mobiles need to store
            int limit = Math.min(5, mobileNames.size());
            
            //Iterate through loop to store mobile elements
            for (int i = 0; i < limit; i++) {
            	
            	// Create a new row (i + 1 to create new row every time)
                XSSFRow row = sheet.createRow(i + 1); 
                
                //write mobile name and prices to each cell
                row.createCell(0).setCellValue(mobileNames.get(i).getText());
                row.createCell(1).setCellValue(mobilePrices.get(i).getText());
            }

            // Create a excel file in project root directory
            FileOutputStream fileOut = new FileOutputStream("Mobiles.xlsx");
            	
            //write workbook data into excel
            workbook.write(fileOut);
            
            fileOut.close();
            workbook.close();
            
        }catch(Exception e) {
        	System.out.println("Error while writing into excel file"+e.getMessage());
        }
    }
}
