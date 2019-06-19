package com.epam.padawans.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TasksPage extends BasePage {

    private static final Logger log = LogManager.getLogger();
    private static String taskName;
    private static int number;
    private static int rowCount;
    public WebElement homeTask = null;

    public TasksPage() {
        List<WebElement> rw = driver.findElements(By.xpath("//div[@class = 'container body-content']//tbody/tr"));
        rowCount = rw.size();
    }

    public TasksPage openPage() {
        driver.navigate().to("http://10.6.221.51/Tasks");
        log.info("Open Tasks page");
        return new TasksPage();
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getNumber() {
        return number;
    }

    public GitHubPage taskClick(int n) {
        number = n;
        taskName = driver.findElement(By.xpath("//div[@class = 'container body-content']//tr[" + number + "]//p")).getText().substring(0, 6).toLowerCase();
        driver.findElement(By.xpath("//div[@class = 'container body-content']//tr[" + number + "]//a")).click();
        log.info("Click on task");
        return new GitHubPage();
    }

    public TasksPage refreshPage() {
        driver.navigate().refresh();
        log.info("Refresh Tasks page");
        return new TasksPage();
    }

    public String getTaskName() {
        log.info("The task name is: " + taskName);
        return taskName;
    }

    public TasksPage chooseRepo() {
        refreshPage();
        List<WebElement> tasks = driver.findElements(By.xpath("//tbody/tr[" + number + "]//*[@id='repositoryId']/option"));
        for (WebElement t : tasks) {
            if (t.getText().toLowerCase().contains(taskName)) {
                homeTask = t;
            }
        }
        if (homeTask != null) {
            homeTask.click();
            log.info("Choose needed repo: " + taskName);
        }
        return this;
    }

    public HomeWorksPage chooseRepoAndSubmit() {
        chooseRepo();
        String buttonSubmitLocator = "//div[@class = 'container body-content']//tr[" + number + "]//button";
        driver.findElement(By.xpath(buttonSubmitLocator)).click();
        log.info("Click on [Submit] button");
        return new HomeWorksPage();
    }

    public String getPageText() {
        return driver.getPageSource();
    }
}