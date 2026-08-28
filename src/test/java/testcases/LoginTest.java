package testcases;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ConfigUtils;

@Feature("Auth Feature")
public class LoginTest extends BaseTest {

    @Story("Login with Email and Password")
    @Description("It will login by filling the email and the password and navigate to todo page")
    @Test(description = "User should login successfully using valid credentials")
    public void shouldBeAbleToLogin() {

        boolean isWelcomeDisplayed = new LoginPage(getDriver())
                .load()
                .login(
                        ConfigUtils.getInstance().getEmail(),
                        ConfigUtils.getInstance().getPassword()
                )
                .isWelcomeMessageDisplayed();

        Assert.assertTrue(isWelcomeDisplayed);

    }

}