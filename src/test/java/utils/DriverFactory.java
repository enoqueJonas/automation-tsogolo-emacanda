package utils;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {
    private static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

    // Using a remote URL - the tests will run on a docker container (selenium/standalone-chrome)
    private static final String REMOTE_URL =
            System.getenv("SELENIUM_REMOTE_URL") != null
                    ? System.getenv("SELENIUM_REMOTE_URL")
                    : "http://localhost:4444/wd/hub";

    public static void initDriver(String browser) throws MalformedURLException {
        switch (browser.toLowerCase()) {
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                driver.set(new RemoteWebDriver(new URL(REMOTE_URL), firefoxOptions));
                break;

            case "chromium": // map chromium → chrome
            case "chrome":
            default:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless=new");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                driver.set(new RemoteWebDriver(new URL(REMOTE_URL), chromeOptions));
                break;
        }
    }
    public static RemoteWebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}