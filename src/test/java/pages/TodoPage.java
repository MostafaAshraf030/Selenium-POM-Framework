package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.ConfigUtils;
import utils.EndPoints;

public class TodoPage extends BasePage {

    public TodoPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[data-testid='welcome']")
    private WebElement welcomeMessage;

    @FindBy(css = "[data-testid='add']")
    private WebElement addButton;

    @FindBy(css = "[data-testid='todo-item']")
    private WebElement todoItem;

    @FindBy(css = "[data-testid='delete']")
    private WebElement deleteButton;

    @FindBy(css = "[data-testid='no-todos']")
    private WebElement noTodosMessage;

    @Step
    public TodoPage load() {

        driver.get(ConfigUtils.getInstance().getBaseUrl() + EndPoints.TODO_PAGE_ENDPOINT);

        return this;
    }

    @Step
    public boolean isWelcomeMessageDisplayed() {
        return isDisplayed(welcomeMessage);
    }

    @Step
    public NewTodoPage clickOnAddButton() {

        click(addButton);

        return new NewTodoPage(driver);
    }

    @Step
    public String isTaskAdded() {

        return getText(todoItem);
    }

    @Step
    public TodoPage clickOnDeleteButton() {

        click(deleteButton);

        return this;
    }

    @Step
    public boolean isTaskDeleted() {

        return isDisplayed(noTodosMessage);
    }

}