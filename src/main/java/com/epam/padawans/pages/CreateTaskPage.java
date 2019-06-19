package com.epam.padawans.pages;

import com.epam.padawans.utils.RandomUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateTaskPage extends BasePage {

    private static final Logger log = LogManager.getLogger();

    @FindBy(xpath = "//input[@id='Name']")
    private WebElement formNameNewTask;

    @FindBy(xpath = "//input[@id='GitHubUrl']")
    private WebElement formGitHubURLNewTask;

    @FindBy(xpath = " //input[@id='TestsFileName']")
    private WebElement formTestFileNameNewTask;

    @FindBy(xpath = "//input[@value='Create']")
    private WebElement buttonCreate;

    @FindBy(xpath = "//input[@value='Save']")
    private WebElement buttonSave;

    public String enterNameTask() {
        String name = RandomUtils.getRandomIntAndString(30);
        formNameNewTask.sendKeys(name);
        log.info("Enter task name: " + name);
        return name;
    }

    public ManageTaskPage editNameAndSaveChanges(String newName) {
        formNameNewTask.clear();
        formNameNewTask.sendKeys(newName);
        buttonSave.click();
        log.info("New task name: " + newName);
        return new ManageTaskPage();
    }

    public String enterGitHubURLTask() {
        String url = RandomUtils.getRandomIntAndString(15);
        formGitHubURLNewTask.sendKeys(url);
        log.info("Enter githubURL task:'" + url);
        return url;
    }

    public ManageTaskPage createTask() {
        buttonCreate.click();
        log.info("New project was created'");
        return new ManageTaskPage();
    }
}
