package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormPage {

    private final WebDriver driver;

    // Finding all the necessary elements on the page
    private final By firstAnswer = By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div/span/div/div[1]/label/div/div[2]/div/span");
    private final By btnNext = By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[3]/div[1]/div[1]/div/span/span");
    private final By empMessage = By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[1]/div/div/div");

    public FormPage(WebDriver driver) {
        // Initializing the driver
        this.driver = driver;
    }

    // Oppening the page
    public void open() {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSduDsmTlW9nJlPMVTNq9MO5sN5jvLGoE3QwoQhwMyJIGbfzVg/formResponse");
    }

    // Performing the login on the page
    public void login() {
        driver.findElement(firstAnswer).click();
        driver.findElement(btnNext).click();
    }

    // Finding the success message
    public String getSuccessMessage() {
        return driver.findElement(empMessage).getText();
    }
}