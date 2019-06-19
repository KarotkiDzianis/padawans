package com.epam.padawans.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;

public class ReportViewPage extends BasePage {

    private static final Logger log = LogManager.getLogger();

    @FindBy(xpath = "//div[@title = 'Count of all passed tests']")
    private WebElement fixtureSummary;

    @FindBy(xpath = "//div[@title = 'Count of all failed tests']")
    private WebElement testsSummary;

    @FindBy(xpath = "//div[@title = 'Count of all inconclusive tests']")
    private WebElement passPercentage;

    @FindBy(xpath = "//label[contains(text(), 'Filter Suites')]")
    private WebElement filterSuites;

    @FindBy(xpath = "//label[contains(text(), 'Filter Tests')]")
    private WebElement filterTests;

    public boolean allElementsArePresentedOnThePage() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        boolean a = fixtureSummary.isDisplayed() & testsSummary.isDisplayed() & passPercentage.isDisplayed()
                & filterSuites.isDisplayed() & filterTests.isDisplayed();
        driver.close();
        driver.switchTo().window(tabs.get(0));
        log.info("All of elementas are presented on the page: " + a);
        return a;
    }
}
