package base;

import factory.DriverFactory;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.http.Cookie;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigUtils;
import utils.CookieUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class BaseTest {

    protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }

    public WebDriver getDriver() {
        return this.driver.get();
    }

    @BeforeMethod
    public void setup() {
        WebDriver driver = new DriverFactory().initializeDriver();
        setDriver(driver);
    }

    @AfterMethod
    public void teardown(ITestResult result) {

        String testCaseName = result.getMethod().getMethodName();
        File destFile = new File("target" + File.separator + "screenshots" + File.separator + testCaseName + ".png");
        takeScreenShot(destFile);

        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }

    }

    @Step
    public void injectCookiesToBrowser(List<Cookie> restAssuredCookies){

        getDriver().get(ConfigUtils.getInstance().getBaseUrl());

        List<org.openqa.selenium.Cookie> seleniumCookies =
                CookieUtils.convertRestAssuredCookiesToSeleniumCookies(restAssuredCookies);

        seleniumCookies.forEach(getDriver().manage()::addCookie);

        getDriver().navigate().refresh();
    }

    @Step
    public void takeScreenShot(File destFile) {
        File file = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, destFile);
            try (InputStream is = new FileInputStream(destFile)) {
                Allure.addAttachment("screenshot", is);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to save or attach screenshot: " + e.getMessage(), e);
        }
    }

}