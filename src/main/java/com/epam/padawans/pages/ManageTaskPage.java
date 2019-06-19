package com.epam.padawans.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManageTaskPage extends BasePage {

    private static final Logger log = LogManager.getLogger();
    private int number;
    private Map<String, Integer> map;
    private WebElement buttonEdit;

    @FindBy(xpath = "//*[@href='/Tasks/Create']")
    private WebElement buttonCreate;

    @FindBy(xpath = "//tbody/tr")
    private List<WebElement> listTask;

    @FindBy(xpath = "tbody/tr[5]/td[2]/p")
    private WebElement last;

    public CreateTaskPage goToCreateTasks() {
        buttonCreate.click();
        log.info("Click on button 'Create'");
        return new CreateTaskPage();
    }

    public CreateTaskPage editTaskByName(String taskName) {
        buttonEdit = driver.findElement(By.xpath("//a[contains(text(),'" + taskName + "')]/ancestor::td/following-sibling::td/a"));
        buttonEdit.click();
        log.info("Click on button 'Edit'");
        return new CreateTaskPage();
    }

    public String readNameLastTask() {
        number = listTask.size();
        log.info("Size list of projects is " + listTask.size());
        return driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[" + number + "]/td[2]/p")).getText();
    }

    public boolean checkChangesAndThenDelete(String taskName) {
        if (driver.findElement(By.xpath("//a[contains(text(),'" + taskName + "')]")).isDisplayed()) {
            findAndDeleteNewProject(taskName);
            return true;
        } else {
            return false;
        }
    }

    public boolean findAndDeleteNewProject(String taskName) {
        number = listTask.size();
        log.info("Size projects is " + listTask.size());
        map = new HashMap<>();
        for (int i = 1; i <= number; i++) {
            map.put(driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[" + i + "]/td[2]/p")).getText(), i);
        }
        log.info("This all projects -" + map.toString());
        if (map.containsKey(taskName)) {
            int number = map.get(taskName);
            driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[" + number + "]/td[4]/form/button")).click();
            log.info("Project " + taskName + " was deleted ");
            return true;
        } else {
            return false;
        }
    }
}
