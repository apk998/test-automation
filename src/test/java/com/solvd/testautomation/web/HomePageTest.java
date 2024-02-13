package com.solvd.testautomation.web;

import com.solvd.testautomation.web.components.Footer;
import com.solvd.testautomation.web.components.Header;
import com.solvd.testautomation.web.components.ProductCard;
import com.solvd.testautomation.web.components.SearchLineComponent;
import com.zebrunner.carina.core.AbstractTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class HomePageTest extends AbstractTest {

    @Test
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

    @Test
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

    @Test
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

    @Test
    public void verifyFooterLinkTest() {
        SoftAssert sa = new SoftAssert();
        WebDriver driver = getDriver();

        HomePage homePage = new HomePage(driver);
        homePage.open();

        Footer footer = homePage.getFooter();
        footer.clickPrivacyPolicyLink();

        PrivacyPolicyPage privacyPolicyPage = new PrivacyPolicyPage(driver);
        sa.assertTrue(driver.getCurrentUrl().contains("privacy-policy"), "URL does not contain 'privacy-policy'");
        sa.assertEquals(privacyPolicyPage.isPrivacyPolicyPageDisplayed(),
                "Privacy Policy", "Incorrect page title after navigating to Privacy Policy page");

        sa.assertAll();
    }
}
