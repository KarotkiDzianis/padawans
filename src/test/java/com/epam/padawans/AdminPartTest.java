package com.epam.padawans;

import com.epam.padawans.pages.CreateTaskPage;
import com.epam.padawans.pages.LoginPage;
import com.epam.padawans.pages.PadawansHomePage;
import com.epam.padawans.utils.RandomUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminPartTest extends BaseTest {

    private CreateTaskPage createTaskPage;
    private LoginPage loginPage;

    /**
     * [EPMFARMATS-7358]
     */
    @Test
    public void createAndThenDeleteTask() {
        createTaskPage = new PadawansHomePage()
                .openPage()
                .loginLinkClick()
                .buttonGitHubClick()
                .login(LOGIN, PASSWORD)
                .goToManagePage()
                .goToCreateTasks();
        String taskName = createTaskPage.enterNameTask();
        createTaskPage.enterGitHubURLTask();
        Assert.assertTrue(createTaskPage.createTask().findAndDeleteNewProject(taskName));
    }

    /**
     * [EPMFARMATS-7360]
     */
    @Test
    public void findElementsOnLoginPage() {
        loginPage = new PadawansHomePage()
                .openPage()
                .loginLinkClick();
        Assert.assertTrue(loginPage.elementIsDispalyed());
    }

    /**
     * [EPMFARMATS-7361]
     */
    @Test
    public void editTaskForStudents() {
        createTaskPage = new PadawansHomePage()
                .openPage()
                .loginLinkClick()
                .buttonGitHubClick()
                .login(LOGIN, PASSWORD)
                .goToManagePage()
                .goToCreateTasks();
        String taskName = createTaskPage.enterNameTask();
        String newTaskName = RandomUtils.getRandomIntAndString(10);
        createTaskPage.enterGitHubURLTask();
        Assert.assertTrue(createTaskPage.createTask()
                .editTaskByName(taskName)
                .editNameAndSaveChanges(newTaskName)
                .checkChangesAndThenDelete(newTaskName));
    }
}
