package com.epam.padawans;

import com.epam.padawans.pages.HomeWorksPage;
import com.epam.padawans.pages.PadawansHomePage;
import com.epam.padawans.pages.TasksPage;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class StudentsPartTest extends BaseTest {

    private static final String NAME = "Padawans .NET";
    private PadawansHomePage padawansHomePage;
    private HomeWorksPage homeWorksPage;
    private TasksPage tasksPage;

    /**
     * [EPMFARMATS-7337]
     */
    @Test
    public void loginTest() {
        padawansHomePage = new PadawansHomePage()
                .openPage()
                .loginLinkClick()
                .buttonGitHubClick()
                .login(LOGIN, PASSWORD);
        assertTrue(padawansHomePage.getPageText().contains("Log off") &
                padawansHomePage.getPageText().contains(LOGIN));
    }

    /**
     * [EPMFARMATS-7341]
     */
    @Test
    public void logoutTest() {
        padawansHomePage = new PadawansHomePage()
                .openPage()
                .loginLinkClick()
                .buttonGitHubClick()
                .login(LOGIN, PASSWORD)
                .logOutLinkClick();
        assertTrue(padawansHomePage.getPageText().contains("Log in") & padawansHomePage.getPageText().contains("Home")
                & padawansHomePage.getPageText().contains(NAME));
    }

    /**
     * [EPMFARMATS-7345]
     */
    @Test
    public void repoLinkOnHomeWorksPageIsCorrectTest() {
        SoftAssert softAssert = new SoftAssert();
        homeWorksPage = new PadawansHomePage()
                .openPage()
                .loginLinkClick()
                .buttonGitHubClick()
                .login(LOGIN, PASSWORD)
                .goToHomeWorksPage();
        List<Boolean> results = new ArrayList<>();
        for (int i = 1; i <= homeWorksPage.getRowCount(); i++) {
            String homeWorkUrl = homeWorksPage.studentHomeWorkClick(i).getStudentHomeWorkLink();
            String taskName = homeWorksPage.getTaskName(i);
            results.add(homeWorkUrl.contains(taskName));
            homeWorksPage.openPage();
        }
        for (Boolean a : results) {
            softAssert.assertTrue(a, "Not all repo links are correct.");
        }
        softAssert.assertAll();
    }

    /**
     * [EPMFARMATS-7346]
     */
    @Test
    public void availableTasksCountIsNotNullTest() {
        tasksPage = new PadawansHomePage()
                .openPage()
                .loginLinkClick()
                .buttonGitHubClick()
                .login(LOGIN, PASSWORD)
                .goToTaskPage();
        int rowCount = tasksPage.getRowCount();
        assertTrue(tasksPage.getPageText().contains("Name") & tasksPage.getPageText().contains("Link") &
                tasksPage.getPageText().contains("Submit Home Work"), "Not all Tabs are presented on the page.");
        assertTrue(rowCount > 0, "No available tasks on the page.");
    }

    /**
     * [EPMFARMATS-7347]
     */
    @Test
    public void viewReportTest() {
        SoftAssert softAssert = new SoftAssert();
        homeWorksPage = new PadawansHomePage()
                .openPage().loginLinkClick()
                .buttonGitHubClick()
                .login(LOGIN, PASSWORD)
                .goToHomeWorksPage();
        List<Boolean> results = new ArrayList<>();
        for (int i = 1; i <= homeWorksPage.getRowCount(); i++) {
            results.add(homeWorksPage.goToViewReportPage(i).allElementsArePresentedOnThePage());
            homeWorksPage.openPage();
        }
        for (Boolean a : results) {
            softAssert.assertTrue(a, "Not all elements on the Report Page are presented.");
        }
        softAssert.assertAll();
    }

    /**
     * [EPMFARMATS-7348]
     */
    @Test
    public void isForkedRepoInDropdownListTest() {
        SoftAssert softAssert = new SoftAssert();
        tasksPage = new PadawansHomePage()
                .openPage()
                .loginLinkClick()
                .buttonGitHubClick()
                .login(LOGIN, PASSWORD)
                .goToTaskPage();
        List<WebElement> results = new ArrayList<>();
        for (int i = 1; i <= tasksPage.getRowCount(); i++) {
            results.add(tasksPage.taskClick(i).forkRepoButtonClick().chooseRepo().homeTask);
        }
        for (WebElement a : results) {
            softAssert.assertTrue(a != null, "Not all repo's are presented in dropdown list.");
        }
        softAssert.assertAll();
    }
}