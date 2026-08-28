package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class DriverFactory {

    public WebDriver initializeDriver() {

        WebDriver driver;

        String browser =
                System.getProperty("browser", "chrome").toUpperCase();

        switch (browser) {

            case "CHROME":
                ChromeOptions chromeOptions = new ChromeOptions();

                // Required for GitHub Actions / Linux CI environment
                if (System.getenv("GITHUB_ACTIONS") != null) {
                    chromeOptions.addArguments("--headless=new");
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                }

                chromeOptions.addArguments("--window-size=1920,1080");

                driver = new ChromeDriver(chromeOptions);
                break;

            case "FIREFOX":
                driver = new FirefoxDriver();
                break;

            case "EDGE":
                driver = new EdgeDriver();
                break;

            default:
                throw new RuntimeException(browser + " Browser is not supported");
        }

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));

        return driver;
    }
}

