package com.solvd.testautomation.web;

import com.solvd.testautomation.parsers.PasswordParser;
import com.solvd.testautomation.parsers.UsernameParser;
import com.solvd.testautomation.web.components.Header;
import com.zebrunner.carina.core.AbstractTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;

public class LoginPageTest extends AbstractTest {

    @Test(description = "verify user registration with valid inputs")
    public void verifyUserRegistrationWithValidInputTest() {
        SoftAssert sa = new SoftAssert();
        WebDriver driver = getDriver();

        HomePage homePage = new HomePage(driver);
        homePage.open();

        Header header = homePage.getHeader();
        header.clickCreateAccount();
        RegisterPage registerPage = new RegisterPage(driver);

        String email = generateDummyEmail();

        registerPage.enterFirstName("Thomas");
        registerPage.enterLastName("Paine");
        registerPage.enterEmail(email);
        registerPage.enterDOB("02/09/1990");
        registerPage.clickSignUpButton();

        sa.assertTrue(registerPage.isConfirmationMessagePresent(), "Confirmation message not found");
        sa.assertAll();
    }

    @Test(description = "verify user registration with invalid inputs")
    public void verifyUserRegistrationWithInvalidInputTest() {
        SoftAssert sa = new SoftAssert();
        WebDriver driver = getDriver();

        HomePage homePage = new HomePage(driver);
        homePage.open();

        Header header = homePage.getHeader();
        header.clickCreateAccount();
        RegisterPage registerPage = new RegisterPage(driver);

        String email = generateDummyEmail();

        registerPage.enterFirstName("Thomas");
        registerPage.enterLastName("Paine");
        registerPage.enterEmail(email);
        registerPage.clickSignUpButton();

        sa.assertFalse(registerPage.isConfirmationMessagePresent(), "Error in confirmation message absence");
        sa.assertAll();
    }

    @Test(description = "verify login functionality with valid inputs")
    public void verifyLoginFunctionalityWithValidCredentialsTest() {
        SoftAssert sa = new SoftAssert();
        WebDriver driver = getDriver();

        UsernameParser usernameParser = new UsernameParser(new File("src/main/resources/settings.xml"));
        PasswordParser passwordParser = new PasswordParser(new File("src/main/resources/settings.xml"));

        HomePage homePage = new HomePage(driver);
        homePage.open();

        Header header = homePage.getHeader();
        header.clickLogin();
        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterEmail(usernameParser.parseElement("userEmail", 2));
        loginPage.enterPassword(passwordParser.parseElement("password", 3));
        loginPage.clickLoginButton();

        DashboardPage dashboardPage = new DashboardPage(driver);
        sa.assertEquals(driver.getCurrentUrl(), "https://www.sanrio.com/account", "Incorrect URL after login");
        sa.assertEquals(dashboardPage.isDashboardPageDisplayed(),
                "Account Details", "Incorrect page title after login");

        sa.assertAll();
    }

    @Test(description = "verify login functionality with invalid inputs")
    public void verifyLoginFunctionalityWithInvalidCredentialsTest() {
        SoftAssert sa = new SoftAssert();
        WebDriver driver = getDriver();

        HomePage homePage = new HomePage(driver);
        homePage.open();

        Header header = homePage.getHeader();
        header.clickLogin();
        LoginPage loginPage = new LoginPage(driver);

        String email = generateDummyEmail();

        loginPage.enterEmail(email);
        loginPage.enterPassword("TestCase123");
        loginPage.clickLoginButton();

        sa.assertTrue(loginPage.isInvalidAlertPresent(), "Invalid credentials alert not found");
        sa.assertAll();
    }

    private String generateDummyEmail() {
        return "testuser" + System.currentTimeMillis() + "@example.com";
    }
}
