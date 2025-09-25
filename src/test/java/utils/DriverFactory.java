package utils;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.time.Duration;

public class DriverFactory {
    private static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

    // Remote URL can be set via environment variable or defaults to localhost
    private static final String REMOTE_URL =
            System.getenv("SELENIUM_REMOTE_URL") != null
                    ? System.getenv("SELENIUM_REMOTE_URL")
                    : "http://localhost:4444/wd/hub";

    public static void initDriver(String browser) {
        int attempts = 0;
        final int maxAttempts = 5;
        while (attempts < maxAttempts) {
            try {
                switch (browser.toLowerCase()) {
                    case "firefox":
                        FirefoxOptions firefoxOptions = new FirefoxOptions();
                        firefoxOptions.addArguments("--headless");
                        firefoxOptions.setPageLoadTimeout(Duration.ofSeconds(30));
                        driver.set(new RemoteWebDriver(new URL(REMOTE_URL), firefoxOptions));
                        break;

                    case "chromium":
                    case "chrome":
                    default:
                        ChromeOptions chromeOptions = new ChromeOptions();
                        chromeOptions.addArguments("--headless=new");
                        chromeOptions.addArguments("--no-sandbox");
                        chromeOptions.addArguments("--disable-dev-shm-usage");
                        chromeOptions.setPageLoadTimeout(Duration.ofSeconds(30));
                        driver.set(new RemoteWebDriver(new URL(REMOTE_URL), chromeOptions));
                        break;
                }
                // Successfully created session, exit loop
                break;
            } catch (Exception e) {
                attempts++;
                System.out.println("Attempt " + attempts + " failed to connect to Selenium Hub. Retrying...");
                try {
                    Thread.sleep(2000); // wait 2 seconds before retry
                } catch (InterruptedException ignored) {}
                if (attempts == maxAttempts) {
                    throw new RuntimeException("Could not connect to Selenium Hub at " + REMOTE_URL, e);
                }
            }
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