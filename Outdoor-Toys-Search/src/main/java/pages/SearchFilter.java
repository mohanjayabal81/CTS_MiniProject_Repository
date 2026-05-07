package pages;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import excel.ExcelWriter;
import utils.DriverManager;

public class SearchFilter {

    // WebDriver instance used to interact with Advanced Search page
    protected WebDriver driver;

    // Initialize driver using constructor injection
    public SearchFilter(WebDriver driver) throws IOException {
        this.driver = driver;
    }

    // Opens Advanced Search section from landing page
    public void advancesSearch() {
        try {
            WebElement adBtn = driver.findElement(By.className("gh-search-button__advanced-link"));
            adBtn.click();
        } catch (Exception e) {
            System.out.println("1st");
        }
    }

    // Selects category keywords and keyword matching option
    public void selectCategory() {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            // Enter search keyword
            WebElement itemNumber_inputBox =
                    driver.findElement(By.className("textbox__control"));
            itemNumber_inputBox.sendKeys("outdoor toys");

            // Select keyword match condition from dropdown
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement Keyword_optionsDropdown =
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("_in_kw")));

            Select keyopt = new Select(Keyword_optionsDropdown);
            keyopt.selectByVisibleText("Any words, any order");

        } catch (Exception e) {
            System.out.println("2nd");
        }
    }

    // Enables search in title and description using JavaScript click
    public void searchInclude() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchInclude =
                wait.until(ExpectedConditions.presenceOfElementLocated(By.name("LH_TitleDesc")));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", searchInclude);
    }

    // Selects item condition radio button
    public void conditioRadioBox() throws InterruptedException {
        WebElement conditionratiobox = driver.findElement(
                By.xpath("//fieldset[@class='adv-fieldset__condition']//div[1]//span[1]//input[1]"));
        conditionratiobox.click();
        Thread.sleep(2000); 
    } 
	

    // Enables required result filters
    public void showResult() {
        WebElement showresultchkbox = driver.findElement(
                By.xpath("//fieldset[@class='adv-fieldset__showResults']/div[1]/descendant::label"));
        showresultchkbox.click();

        WebElement showresultchkbox2 = driver.findElement(
                By.xpath("//fieldset[@class='adv-fieldset__showResults']/div[2]/descendant::label"));
        showresultchkbox2.click();
    }

    // Filters results based on item location
    public void itemLocation() {
        WebElement location = driver.findElement(
                By.xpath("//fieldset[@class='adv-fieldset__location']/div[4]/descendant::label"));
        location.click();
    }

    // Submits advanced search form
    public void searchClick() {
        WebElement searchbtn = driver.findElement(
                By.xpath("//div[@class='adv-form__actions']//button[@type='submit'][normalize-space()='Search']"));
        searchbtn.click();
    }

    // Utility instances for Excel export
    ExcelWriter ew = new ExcelWriter();
    List<String[]> l = new ArrayList<>();

    // Extracts search results and writes filtered data to Excel
    public void dataRetrivel()
            throws InterruptedException, FileNotFoundException, IOException {

        // Fetch all result cards
        List<WebElement> links =
                driver.findElements(By.xpath("//div[@class='su-card-container__header']"));

        List<String[]> list = new ArrayList<>();
        System.out.println(links.size());

        // Loop through results and extract required fields
        for (int i = 1; i < links.size() - 2; i++) {

            WebElement val = driver.findElement(
                    By.xpath("//*[@id='srp-river-results']/ul/li[" + i + "]/div/div[1]/div/div/div/div/div/a"));
            String linkVal = val.getAttribute("href");

            String name = driver.findElement(
                    By.xpath("//*[@id='srp-river-results']/ul/li[" + i +
                             "]/div/div[2]/div[1]/a/div/span")).getText();

            // Filter only toy-related items
            if (name.toLowerCase().contains("toys")) {
                System.out.println(name + " " + linkVal);
                list.add(new String[] { linkVal, name });
            }
        }

        System.out.println(list.size());

        // Write extracted data into Excel file
        ew.excelWriter(list);
    }
}