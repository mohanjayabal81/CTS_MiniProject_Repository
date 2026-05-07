 package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.SearchFilter;

public class TestSearchPage extends BaseTest {

    // Page object reference for Advanced Search page
    SearchFilter sFilter;

    // Initializes SearchFilter page object before executing tests
    @BeforeClass
    public void InstalizePage() throws IOException {
        sFilter = new SearchFilter(driver);
    }

    // Navigates to Advanced Search page
    @Test(priority = 1)
    public void advancesSearch() {
        sFilter.advancesSearch();
    }

    // Selects category and keyword options
    @Test(dependsOnMethods = { "advancesSearch" })
    public void selectCategory() {
        sFilter.selectCategory();
    }

    // Enables search in title and description
    @Test(dependsOnMethods = { "selectCategory" })
    public void searchInclude() throws InterruptedException {
        sFilter.searchInclude();
    }

    // Selects item condition filter
    @Test(dependsOnMethods = { "searchInclude" })
    public void conditioRadioBox() throws InterruptedException {
        sFilter.conditioRadioBox();
    }

    // Applies result visibility filters
    @Test(dependsOnMethods = { "conditioRadioBox" })
    public void showResult() {
        sFilter.showResult();
    }

    // Filters results based on item location
    @Test(dependsOnMethods = { "showResult" })
    public void item_Location() {
        sFilter.itemLocation();
    }

    // Submits the advanced search form
    @Test(dependsOnMethods = { "item_Location" })
    public void searchClick() {
        sFilter.searchClick();
    }

    // Extracts search results and writes them into Excel
    @Test(priority = 8)
    public void dataIntoExcel()
            throws FileNotFoundException, InterruptedException, IOException {
        sFilter.dataRetrivel();
    }
}