package com.epam.padawans.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;

public class GitHubPage extends BasePage {

    private static final Logger log = LogManager.getLogger();

    @FindBy(xpath = "//ul[@class='pagehead-actions']//button[contains(@aria-label,'Fork')]")
    private WebElement forkRepoButton;

    public TasksPage forkRepoButtonClick() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        forkRepoButton.click();
        driver.close();
        driver.switchTo().window(tabs.get(0));
        log.info("Click on [Fork] button");
        return new TasksPage();
    }

    public String getStudentHomeWorkLink() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        String url = driver.getCurrentUrl().toLowerCase();
        log.info("Student home work repo link is : " + url);
        driver.close();
        driver.switchTo().window(tabs.get(0));
        return url;
    }
}