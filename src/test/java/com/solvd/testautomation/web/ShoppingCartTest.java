package com.solvd.testautomation.web;

import com.solvd.testautomation.web.components.Header;
import com.zebrunner.carina.core.AbstractTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ShoppingCartTest extends AbstractTest {

    @Test(description = "verify that shopping cart/bag icon displays correct number of items")
    public void verifyAddToCartTest() {
        SoftAssert sa = new SoftAssert();
        WebDriver driver = getDriver();

        String productUrl = "https://www.sanrio.com/collections/my-melody/products/my-melody-airpods-case";
        driver.get(productUrl);
        HomePage homePage = new HomePage(driver);

        ProductPage productPage = new ProductPage(driver);
        productPage.addToCart();

        Header header = homePage.getHeader();
        int expectedItemCount = 1;
        int actualItemCount = header.getCartItemCount();

        sa.assertEquals(actualItemCount, expectedItemCount, "Cart icon does not display the correct number of items");
        sa.assertAll();
    }

    @Test(description = "verify that cart summary updates accordingly when removing items")
    public void verifyItemRemovalTest() {
        SoftAssert sa = new SoftAssert();
        WebDriver driver = getDriver();

        String productUrl = "https://www.sanrio.com/collections/my-melody/products/my-melody-airpods-case";
        driver.get(productUrl);

        ProductPage productPage = new ProductPage(driver);
        productPage.addToCart();

        Header header = new HomePage(driver).getHeader();
        Actions actions = new Actions(driver);
        actions.moveToElement(header.getCartItemCountElement()).perform();

        ShoppingCartPage cartPage = new ShoppingCartPage(driver);
        cartPage.removeCartItem();

        sa.assertTrue(cartPage.isCartEmpty(), "Cart is not empty after removing the item");
        sa.assertAll();
    }
}
