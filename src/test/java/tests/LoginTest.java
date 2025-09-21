package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import utils.DriverFactory;

import java.net.MalformedURLException;

public class LoginTest {

    private LoginPage loginPage;

    @BeforeTest(alwaysRun = true)
    @Parameters("browserType")
    public void setUp(@Optional("chrome") String browser) throws MalformedURLException {
        // Use the parameter from testng.xml or default to "chrome"
        DriverFactory.initDriver(browser);
        loginPage = new LoginPage(DriverFactory.getDriver());
    }

    @Test
    public void validLoginTest() {
        loginPage.open();
        loginPage.login("student", "Password123");
        String expected = "Logged In Successfully";
        Assert.assertEquals(loginPage.getSuccessMessage(), expected);
    }

    @Test
    public void invalidUsernameTest() throws InterruptedException {
        loginPage.open();
        loginPage.login("st", "Password123");
        String text = "";

        for (int i = 0; i < 5; i++) {
            text = loginPage.getErrorMessage().trim();
            if (!text.isEmpty()) break;
            Thread.sleep(500);
        }
        String expected = "Your username is invalid!";
        Assert.assertEquals(text, expected);  // ✅ use text instead of re-fetching
    }

    @Test
    public void invalidPasswordTest() throws InterruptedException {
        loginPage.open();
        loginPage.login("student", "Wrong");
        String text = "";

        for (int i = 0; i < 5; i++) {
            text = loginPage.getErrorMessage().trim();
            if (!text.isEmpty()) break;
            Thread.sleep(500);
        }
        String expected = "Your password is invalid!";
        Assert.assertEquals(text, expected);  // ✅ use text instead of re-fetching
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}