package com.epam.padawans.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PadawansHomePage extends BasePage {

    private static final Logger log = LogManager.getLogger();
    private String BASE_URL = "http://10.6.221.51/";

    @FindBy(id = "loginLink")
    private WebElement loginLink;

    @FindBy(xpath = "//button[@class = 'navbar-toggler']")
    private WebElement menuIcon;

    @FindBy(xpath = "//*[@id='navbarNav']//a[@href = '/Tasks']")
    private WebElement tasksTab;

    @FindBy(xpath = "//*[@id='navbarNav']//a[@href = '/HomeWorks']")
    private WebElement homeWorksTab;

    @FindBy(id = "logoutForm")
    private WebElement logoutLink;

    @FindBy(xpath = "//*[@href='/Tasks/ManageTasks']")
    private WebElement linkManageTasks;

    public LoginPage loginLinkClick() {
        if (menuIcon.isDisplayed()) {
            menuIcon.click();
        }
        loginLink.click();
        log.info("Click on login link");
        return new LoginPage();
    }

    public PadawansHomePage openPage() {
        driver.get(BASE_URL);
        log.info("Open page with URL: " + BASE_URL);
        return new PadawansHomePage();
    }

    public TasksPage goToTaskPage() {
        tasksTab.click();
        log.info("Click on Task tab");
        return new TasksPage();
    }

    public HomeWorksPage goToHomeWorksPage() {
        homeWorksTab.click();
        log.info("Click on Home Works tab");
        return new HomeWorksPage();
    }

    public String getPageText() {
        return driver.getPageSource();
    }

    public PadawansHomePage logOutLinkClick() {
        logoutLink.click();
        log.info("Click on Log off tab");
        return new PadawansHomePage();
    }

    public ManageTaskPage goToManagePage() {
        linkManageTasks.click();
        log.info("Click on Manage Tasks link");
        return new ManageTaskPage();
    }

}