package com.epam.padawans;

import com.epam.padawans.pages.HomeWorksPage;
import com.epam.padawans.pages.PadawansHomePage;
import com.epam.padawans.pages.TasksPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class BuildAllReposTest extends BaseTest {

    private HomeWorksPage homeWorksPage;
    private TasksPage tasksPage;

    @Test
    public void buildAllReposTest() {
        SoftAssert softAssert = new SoftAssert();
        tasksPage = new PadawansHomePage()
                .openPage()
                .loginLinkClick()
                .buttonGitHubClick()
                .login(LOGIN, PASSWORD)
                .goToTaskPage();
        List<Boolean> results = new ArrayList<>();
        for (int i = 1; i <= tasksPage.getRowCount(); i++) {
            homeWorksPage = tasksPage.taskClick(i).forkRepoButtonClick().chooseRepoAndSubmit();
            results.add(homeWorksPage.getResult(tasksPage.getNumber()).contains("Successful"));
            tasksPage.openPage();
        }
        for (Boolean a : results) {
            softAssert.assertTrue(a, "Not all builds were successful.");
        }
        softAssert.assertAll();
    }
}