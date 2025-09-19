package tests;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DriverFactory;

import java.net.MalformedURLException;

public class LoginTest {

    private LoginPage loginPage;

    // Doing the necessary setup before running teh tests
    @BeforeTest
    @Parameters("browserType")
    public void setUp(String browser) throws MalformedURLException {
        DriverFactory.initDriver(browser);
        loginPage = new LoginPage(DriverFactory.getDriver());
    }

    // Test the login with validncredentials
    @Test
    public void validLoginTest() {
        loginPage.open();
        loginPage.login("student", "Password123");
        String expected = "Logged In Successfully";
        Assert.assertEquals(loginPage.getSuccessMessage(), expected);
    }

    // Test the login with invalid username
    @Test
    public void invalidUsernameTest() throws InterruptedException {
        loginPage.open();
        loginPage.login("st", "Password123");
        String text = "";

        // This loop is to make sure we have the error message before doing the assertion
        for (int i = 0; i < 5; i++) {  // 5 attempts
            text = loginPage.getErrorMessage().trim();
            if (!text.isEmpty()) break;
            Thread.sleep(500);  // wait 0.5s between attempts
        }
        String expected = "Your username is invalid!";
        Assert.assertEquals(loginPage.getErrorMessage(), expected);
    }


    // Test the login with invalid password
    @Test
    public void invalidPasswordTest() throws InterruptedException {
        loginPage.open();
        loginPage.login("student", "Wrong");
        String text = "";

        // This loop is to make sure we have the error message before doing the assertion
        for (int i = 0; i < 5; i++) {  // 5 attempts
            text = loginPage.getErrorMessage().trim();
            if (!text.isEmpty()) break;
            Thread.sleep(500);  // wait 0.5s between attempts
        }
        String expected = "Your password is invalid!";
        Assert.assertEquals(loginPage.getErrorMessage(), expected);
    }

    // Doing the cleanup after tests
    @AfterTest
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}