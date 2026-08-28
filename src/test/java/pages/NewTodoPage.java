package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.ConfigUtils;
import utils.EndPoints;

public class NewTodoPage extends BasePage {

    public NewTodoPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[data-testid='new-todo']")
    private WebElement taskNameInput;

    @FindBy(css = "[data-testid='submit-newTask']")
    private WebElement submitTaskButton;

    @Step
    public NewTodoPage load() {

        driver.get(ConfigUtils.getInstance().getBaseUrl() + EndPoints.NEW_TODO_ENDPOINT);

        return this;
    }

    @Step
    public TodoPage addTaskName(String taskName) {

        type(taskNameInput, taskName);

        click(submitTaskButton);

        return new TodoPage(driver);
    }

}