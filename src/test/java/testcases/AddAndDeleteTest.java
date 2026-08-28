package testcases;

import api.RegisterAPI;
import api.TasksAPI;
import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.NewTodoPage;
import pages.TodoPage;
import utils.ConfigUtils;

@Feature("Todo Feature")
public class AddAndDeleteTest extends BaseTest {

    @Story("Add Todo")
    @Description("It will add task by filling the task name and pressing the add button")
    @Test(description = "User should be able to add a new task")
    public void shouldBeAbleToAddTask() {

        RegisterAPI registerAPI = new RegisterAPI().register();

        String taskName = "Learn Java";

        new TasksAPI().addTask(registerAPI.getAccessToken(), taskName);

        injectCookiesToBrowser(registerAPI.getRestAssuredCookies());

        String actualTask = new TodoPage(getDriver()).isTaskAdded();

        Assert.assertEquals(actualTask, taskName);
    }

    @Story("Delete Todo")
    @Description("It will delete task by pressing the delete button")
    @Test(description = "User should be able to delete task")
    public void shouldBeAbleToDeleteTask() {

        RegisterAPI registerAPI = new RegisterAPI().register();

        new TasksAPI().addTask(registerAPI.getAccessToken(), "Learn Java");

        injectCookiesToBrowser(registerAPI.getRestAssuredCookies());

        boolean isDeleted = new TodoPage(getDriver())
                .clickOnDeleteButton()
                .isTaskDeleted();

        Assert.assertTrue(isDeleted);
    }

}