package com.epam.padawans.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GitHubLoginPage extends BasePage {

    private static final Logger log = LogManager.getLogger();

    @FindBy(id = "login_field")
    private WebElement loginField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id='login']//*[@value='Sign in']")
    private WebElement buttonSignIn;

    private void loginFieldSendKeys(String login) {
        loginField.click();
        loginField.clear();
        loginField.sendKeys(login);
        log.info("Enter login: " + login);
    }

    private void passwordFieldSendKeys(String password) {
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(password);
        log.info("Enter password: " + password);
    }

    private void buttonSignInClick() {
        buttonSignIn.click();
        log.info("Click on [Sign in] button");
    }

    public PadawansHomePage login(String login, String password) {
        loginFieldSendKeys(login);
        passwordFieldSendKeys(password);
        buttonSignInClick();
        return new PadawansHomePage();
    }
}