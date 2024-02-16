package com.solvd.testautomation.web;

import com.solvd.testautomation.web.components.*;
import com.zebrunner.carina.core.AbstractTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class HomePageTest extends AbstractTest {

    @Test(description = "verify that search bar request returns expected results according to input")
    public void verifySearchLineTest() {
        String productType = "Kuromi";

        SoftAssert sa = new SoftAssert();
        WebDriver driver = getDriver();

        HomePage homePage = new HomePage(driver);
        homePage.open();

        SearchLineComponent component = homePage.getHeader().getComponent();
        Assert.assertTrue(component.isSearchInputDisplayed(), "Search input is not present");
        sa.assertEquals(component.getSearchInputPlaceholder(), "What are you looking for?", "Search input has an incorrect placeholder");
        Assert.assertTrue(component.isSearchButtonDisplayed(), "Search button is not present");

        component.typeSearchInputValue(productType);
        SearchPage searchPage = component.clickSearchButton();

        sa.assertTrue(driver.getCurrentUrl().contains(productType.toLowerCase()), "URL does not contain product type");

        searchPage.getCards().forEach(card ->
                sa.assertTrue(
                        card.getTitleText().toLowerCase().contains(productType.toLowerCase()),
                        String.format("Product with name %s doesn't contain the product type in title", card.getTitleText())
                )
        );
        sa.assertAll();
    }

    @Test(description = "verify that correct page is displayed when navigating")
    public void verifyNavigationTest() {
        SoftAssert sa = new SoftAssert();
        WebDriver driver = getDriver();

        HomePage homePage = new HomePage(driver);
        homePage.open();

        Header header = homePage.getHeader();
        header.navigateToCharactersCategory();

        CharacterPage characterPage = new CharacterPage(driver);
        sa.assertTrue(driver.getCurrentUrl().contains("characters"), "URL does not contain 'characters'");
        sa.assertEquals(characterPage.isCharacterPageDisplayed(),
                "Shop All Sanrio Characters", "Incorrect page title after navigating to Characters category");

        sa.assertAll();
    }

    @Test(description = "Verify that only products matching specified criteria are displayed")
    public void verifyFilterTest() {
        SoftAssert sa = new SoftAssert();
        WebDriver driver = getDriver();

        String url = "https://www.sanrio.com/collections/gudetama";
        driver.get(url);

        SearchPage searchPage = new SearchPage(driver);
        searchPage.applyUnderTenFilter();

        List<ProductCard> productCards = searchPage.getCards();
        boolean allPricesUnderTen = productCards.stream()
                .map(card -> Double.parseDouble(card.getProductPrice().substring(1)))
                .allMatch(price -> price < 10.0);

        sa.assertTrue(allPricesUnderTen, "Not all product prices are under $10");

        sa.assertAll();
    }

    @Test(description = "Verify that footer links navigate to the correct page")
    public void verifyFooterLinkTest() {
        SoftAssert sa = new SoftAssert();
        WebDriver driver = getDriver();

        HomePage homePage = new HomePage(driver);
        homePage.open();

        Footer footer = homePage.getFooter();
        footer.clickPrivacyPolicyLink();

        PrivacyPolicyPage privacyPolicyPage = new PrivacyPolicyPage(driver);
        sa.assertTrue(driver.getCurrentUrl().contains("privacy-policy"), "URL does not contain 'privacy-policy'");
        sa.assertTrue(privacyPolicyPage.isPrivacyPolicyPageDisplayed(), "Title text is not displayed on the page");
        sa.assertEquals(privacyPolicyPage.getTitleText(),
                "Privacy Policy", "Incorrect page title after navigating to Privacy Policy page");

        sa.assertAll();
    }

    @Test(description = "verify that link opens a new browser tab correctly")
    public void verifyInstagramOpensInNewTabTest() {
        SoftAssert sa = new SoftAssert();
        WebDriver driver = getDriver();

        HomePage homePage = new HomePage(driver);
        homePage.open();

        Footer footer = homePage.getFooter();
        String mainWindowHandle = driver.getWindowHandle();

        footer.clickInstagramButton();
        switchToNewTab(driver, mainWindowHandle);

        sa.assertEquals(driver.getCurrentUrl(), "https://www.instagram.com/sanrio/",
                "Incorrect URL after opening Instagram link in a new tab");
        sa.assertAll();
    }

    @Test(description = "verify search suggestions are correct")
    public void verifySearchSuggestionsTest() {
        SoftAssert sa = new SoftAssert();
        WebDriver driver = getDriver();

        HomePage homePage = new HomePage(driver);
        homePage.open();

        SearchLineComponent component = homePage.getHeader().getComponent();
        component.typeSearchInputValue("not");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='fast-autocomplete-1235']/div[1]/div[1]/div")));

        List<String> suggestions = component.getSearchSuggestions();

        suggestions.forEach(suggestion ->
                sa.assertTrue(suggestion.toLowerCase().startsWith("not"),
                        String.format("Suggestion '%s' does not start with 'not'", suggestion))
        );
        sa.assertAll();
    }

    @Test(description = "verify that correct page is displayed when navigating using enums")
    public void verifyEnumsTest() {
        SoftAssert sa = new SoftAssert();
        WebDriver driver = getDriver();

        HomePage homePage = new HomePage(driver);
        homePage.open();

        Header header = homePage.getHeader();
        header.navigateToPlushAndToysCategory(ToyCategories.PLUSH);

        sa.assertEquals(driver.getCurrentUrl(),
                "https://www.sanrio.com/collections/plush", "Incorrect URL");
        sa.assertAll();
    }

    private void switchToNewTab(WebDriver driver, String mainWindowHandle) {
        Set<String> windowHandles = driver.getWindowHandles();

        String newTabHandle = windowHandles.stream()
                .filter(handle -> !handle.equals(mainWindowHandle))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No new tab found"));

        driver.switchTo().window(newTabHandle);
    }
}
