 package tests;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.LandingPage;

public class TestLandingPage extends BaseTest {

    // Page object reference for Landing Page
    LandingPage lPage;

    // Initializes page objects before executing test methods
    @BeforeClass
    public void initializePage() throws IOException {
        lPage = new LandingPage(driver);
    }

    // Test case to verify Advanced Search button navigation
    @Test
    public void advancesBtn() {
        lPage.advancesBtn();
    }
}