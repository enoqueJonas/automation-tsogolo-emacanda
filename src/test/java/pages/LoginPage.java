package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final WebDriver driver;

    // Finding all the necessary elements on the page
    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By submitButton = By.id("submit");
    private final By successMessage = By.xpath("//*[@id=\"loop-container\"]/div/article/div[1]/h1");
    private final By errorMessage = By.xpath("//*[@id=\"error\"]");

    public LoginPage(WebDriver driver) {
        // Initializing the driver
        this.driver = driver;
    }

    // Oppening the page
    public void open() {
        driver.get("https://practicetestautomation.com/practice-test-login/");
    }

   // Performing the login on the page
    public void login(String username, String password) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(submitButton).click();
    }

    // Finding the success message
    public String getSuccessMessage() {
        return driver.findElement(successMessage).getText();
    }

    // Finding the error message
    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}