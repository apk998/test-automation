package com.solvd.testautomation.web;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {

    @FindBy(id = "//*[@id='customer_email']")
    private ExtendedWebElement emailInput;

    @FindBy(id = "//*[@id='customer_password']")
    private ExtendedWebElement passwordInput;

    @FindBy(id = "//*[@id='customer_login']/div[3]/input")
    private ExtendedWebElement loginButton;

    @FindBy(id = "//*[@id='customer_login']/div[1]/span")
    private ExtendedWebElement invalidAlert;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public boolean isInvalidAlertPresent() {
        return invalidAlert.isElementPresent(1);
    }
}
