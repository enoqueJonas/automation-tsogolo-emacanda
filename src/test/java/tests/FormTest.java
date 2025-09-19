//package tests;
//
//import org.testng.Assert;
//import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Test;
//import pages.FormPage;
//import pages.LoginPage;
//import utils.DriverFactory;
//
//import java.net.MalformedURLException;
//
//public class FormTest {
//
//    private FormPage formPage;
//
//    // Doing the necessary setup before running teh tests
//    @BeforeTest
//    public void setUp() throws MalformedURLException {
//        DriverFactory.initDriver();
//        formPage = new FormPage(DriverFactory.getDriver());
//    }
//
//    // Test the login with validncredentials
//    @Test
//    public void validLoginTest() {
//        formPage.open();
//        formPage.login();
//        String expected = "Empregador";
//        Assert.assertEquals(formPage.getSuccessMessage(), expected);
//    }
//
//    // Doing the cleanup after tests
//    @AfterTest
//    public void tearDown() {
//        DriverFactory.quitDriver();
//    }
//}