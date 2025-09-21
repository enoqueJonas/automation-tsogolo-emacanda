package utils;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {
    private static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

    // Using a remote URL - the tests will run on a docker container (selenium/standalone-chrome)
    private static final String REMOTE_URL = "http://localhost:4444";

    public static void initDriver(String browser) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        if(browser.equalsIgnoreCase("firefox")){
            capabilities.setCapability("browserName", "firefox");
        }
        if(browser.equalsIgnoreCase("chromium")){
            capabilities.setCapability("browserName", "chrome");
        }
        driver.set(new RemoteWebDriver(new URL(REMOTE_URL), capabilities));
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