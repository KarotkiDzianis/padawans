package com.epam.padawans.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomeWorksPage extends BasePage {

    private static final Logger log = LogManager.getLogger();
    private static final String FAIL = "Fail";
    private static final String ERROR = "Error";
    private static final String SUCCESS = "Successful";
    private static int rowCount;
    private static int number;
    private static String taskName;

    public HomeWorksPage() {
        List<WebElement> rw = driver.findElements(By.xpath("//div[@class = 'container body-content']//tbody/tr"));
        rowCount = rw.size();
    }

    public HomeWorksPage openPage() {
        driver.navigate().to("http://10.6.221.51/HomeWorks");
        log.info("Open Home Works page");
        return new HomeWorksPage();
    }

    public GitHubPage taskClick(int n) {
        number = n;
        taskName = driver.findElement(By.xpath("//div[@class = 'container body-content']//tr[" + number + "]//p")).getText().substring(0, 6).toLowerCase();
        driver.findElement(By.xpath("//div[@class = 'container body-content']//tr[" + number + "]//a")).click();
        log.info("Click on task");
        return new GitHubPage();
    }

    public GitHubPage studentHomeWorkClick(int n) {
        driver.findElement(By.xpath("//div[@class = 'container body-content']//tr[" + n + "]/td[2]/a")).click();
        log.info("Click on student's home work repo link.");
        return new GitHubPage();
    }

    public ReportViewPage goToViewReportPage(int n) {
        driver.findElement(By.xpath("//div[@class = 'container body-content']//tr[" + n + "]/td[6]/a")).click();
        log.info("Click on View Report button.");
        return new ReportViewPage();
    }

    public String getTaskName(int n) {
        String name = driver.findElement(By.xpath("//div[@class = 'container body-content']//tr[" + n + "]//a")).getText().toLowerCase();
        log.info("Task name is: " + name);
        return name;
    }

    public int getRowCount() {
        return rowCount;
    }

    public String getResult(int number) {
        if (driver.getPageSource().contains(ERROR)) {
            log.info("Result: " + ERROR);
            return ERROR;
        }
        String result = driver.findElement(By.xpath("//tr[" + number + "]//p")).getText();
        if (result.contains(SUCCESS)) {
            log.info("Result: " + SUCCESS);
            return SUCCESS;
        } else {
            log.info("Result: " + FAIL);
            return FAIL;
        }
    }
}