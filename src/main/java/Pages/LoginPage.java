package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "username")
    private WebElement usernameInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;

    @FindBy(css = "p.oxd-userdropdown-name")
    private WebElement userProfileDropdown;

    @FindBy(xpath = "//p[text()='Invalid credentials']")
    private WebElement errorMessage;

    @FindBy(xpath = "//p[@class='oxd-text oxd-text--p orangehrm-login-forgot-header']")
    private WebElement forgotPasswordLink;

    @FindBy(xpath = "//span[text()='Required']")
    private WebElement requiredFieldError;

    public void enterUsername(String username) {
        waitForVisibility(usernameInput);
        usernameInput.clear();
        usernameInput.sendKeys(username);
    }

    public void enterPassword(String password) {
        waitForVisibility(passwordInput);
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void clickLogin() {
        waitForClickability(loginButton);
        loginButton.click();
    }

    public void clickForgotPassword() {
        waitForClickability(forgotPasswordLink);
        forgotPasswordLink.click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public boolean isLoginSuccessful() {
        try {
            waitForVisibility(userProfileDropdown);
            return userProfileDropdown.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isErrorMessageDisplayed() {
        try {
            waitForVisibility(errorMessage);
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessageText() {
        try {
            return errorMessage.getText();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean isLoginButtonDisplayed() {
        return loginButton.isDisplayed() && loginButton.isEnabled();
    }

    public boolean areFieldsPresent() {
        return usernameInput.isDisplayed() && passwordInput.isDisplayed();
    }

    public String getUsernamePlaceholder() {
        return usernameInput.getAttribute("placeholder");
    }

    public String getPasswordPlaceholder() {
        return passwordInput.getAttribute("placeholder");
    }

    public boolean isRequiredFieldErrorDisplayed() {
        try {
            waitForVisibility(requiredFieldError);
            return requiredFieldError.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
