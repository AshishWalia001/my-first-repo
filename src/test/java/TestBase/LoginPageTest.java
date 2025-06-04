package TestBase;

import org.testng.annotations.Test;
import Pages.LoginPage;
import static org.testng.Assert.*;

public class LoginPageTest extends BaseTest {
    private LoginPage loginPage;

    @Test(priority = 1)
    public void testInvalidPassword() {
      LoginPage  loginPage = new LoginPage(driver);
        loginPage.login(config.getProperty("username"), "WrongPass123");
        assertFalse(loginPage.isLoginSuccessful(), "Login should fail for invalid password");
        assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed for invalid password");
    }

    @Test(priority = 2)
    public void testInvalidUsername() {
        loginPage = new LoginPage(driver);
        loginPage.login("wrongUser", config.getProperty("password"));
        assertFalse(loginPage.isLoginSuccessful(), "Login should fail for invalid username");
        assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed for invalid username");
    }

    @Test(priority = 3)
    public void testEmptyFields() {
        loginPage = new LoginPage(driver);
        loginPage.login("", "");
        assertFalse(loginPage.isLoginSuccessful(), "Login should fail when fields are empty");
        assertTrue(loginPage.isRequiredFieldErrorDisplayed(), "Required field error should be displayed");
    }

    @Test(priority = 4)
    public void testPlaceholderText() {
        loginPage = new LoginPage(driver);
        assertEquals(loginPage.getUsernamePlaceholder(), "Username", "Username placeholder should be 'Username'");
        assertEquals(loginPage.getPasswordPlaceholder(), "Password", "Password placeholder should be 'Password'");
    }

    @Test(priority = 5)
    public void testForgotPasswordLink() {
        loginPage = new LoginPage(driver);
        assertTrue(loginPage.isLoginButtonDisplayed(), "Login button should be displayed");
        loginPage.clickForgotPassword();
        // Optionally add assertion here to verify the forgot password page
    }

    @Test(priority = 6)
    public void testValidLogin() {
    	driver.get(config.getProperty("baseUrl"));
        loginPage = new LoginPage(driver);
        loginPage.login(config.getProperty("username"), config.getProperty("password"));
        assertTrue(loginPage.isLoginSuccessful(), "Login should succeed with valid credentials");

       
    }
}
