package com.solvd.testautomation.web;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends AbstractPage {

    @FindBy(id = "//*[@id='first-name']")
    private ExtendedWebElement firstNameInput;

    @FindBy(id = "//*[@id='last-name']")
    private ExtendedWebElement lastNameInput;

    @FindBy(id = "//*[@id='email-id']")
    private ExtendedWebElement emailInput;

    @FindBy(id = "//*[@id='dob']")
    private ExtendedWebElement dobInput;

    @FindBy(id = "//*[@id='metafields_form']/div[6]/button")
    private ExtendedWebElement signUpButton;

    @FindBy(id = "//*[@id='metafields_form']/div[1]/p/text()")
    private ExtendedWebElement confirmationMessage;

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void enterFirstName(String firstName) {
        firstNameInput.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        lastNameInput.sendKeys(lastName);
    }

    public void enterEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void enterDOB(String dob) {
        dobInput.sendKeys(dob);
    }

    public void clickSignUpButton() {
        signUpButton.click();
    }

    public boolean isConfirmationMessagePresent() {
        return confirmationMessage.isElementPresent(1);
    }
}
