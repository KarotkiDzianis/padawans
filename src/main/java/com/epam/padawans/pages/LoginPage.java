package com.epam.padawans.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    private static final Logger log = LogManager.getLogger();

    @FindBy(id = "GitHub")
    private WebElement buttonGitHub;

    @FindBy(xpath = "//*[text()='Education is a progressive discovery of our own ignorance.']")
    private WebElement textWelcomeSentence;

    @FindBy(xpath = "//*[contains(text(),'Home')]")
    private WebElement linkHome;

    @FindBy(id = "loginLink")
    private WebElement linkLogIn;

    public GitHubLoginPage buttonGitHubClick() {
        buttonGitHub.click();
        log.info("Click on GitHub button");
        return new GitHubLoginPage();
    }

    public boolean elementIsDispalyed() {
        if (textWelcomeSentence.isDisplayed() && textWelcomeSentence.getText().equals("Education is a progressive discovery of our own ignorance.")
                && linkHome.isDisplayed() && linkLogIn.isDisplayed()) {
            log.info("Page contains text '" + textWelcomeSentence.getText() + " '");
            log.info("Page contains link '" + linkHome.getText() + " '");
            log.info("Page contains link '" + linkLogIn.getText() + " '");
            return true;
        } else return false;
    }
}