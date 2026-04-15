package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ConfigUtils;
import utils.ElementUtils;

public class LoginPage extends BasePage<LoginPage> {

    //Locators
    private final By usernameInput = By.id("username-input");
    private final By passwordInput = By.id("password-input");
    private final By loginButton = By.xpath("//button[.//span[@id='sign-in-label']]");
    private final By errorMessage = By.id("error-message");
    private final By usernameErrorMessage = By.id("username-error");
    private final By passwordErrorMessage = By.id("password-error");
    private final By successScreen = By.id("success-screen");
    private final By successHeading = By.id("success-heading");
    private final By successMessage = By.id("success-message");
    private final By loggedInUsername = By.id("logged-in-username");

    //Methods
    public LoginPage load() {
        String url = ConfigUtils.getInstance().getFileUrl("basepath");
        driver.get(url);
        return this;
    }

    public LoginPage enterUsername(String username) {
        ElementUtils.type(usernameInput, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        ElementUtils.type(passwordInput, password);
        return this;
    }

    public LoginPage clickLogin() {
        ElementUtils.click(loginButton);
        return this;
    }

    public LoginPage login(String username, String password) {
        return enterUsername(username)
                .enterPassword(password)
                .clickLogin();
    }

    public String getErrorMessage() {
        return ElementUtils.getText(errorMessage);
    }

    public boolean isErrorDisplayed() {
        return ElementUtils.isDisplayed(errorMessage);
    }

    public String getUsernameErrorMessage() {
        return ElementUtils.getText(usernameErrorMessage);
    }

    public String getPasswordErrorMessage() {
        return ElementUtils.getText(passwordErrorMessage);
    }

    public boolean isUsernameErrorDisplayed() {
        return ElementUtils.isDisplayed(usernameErrorMessage);
    }

    public boolean isPasswordErrorDisplayed() {
        return ElementUtils.isDisplayed(passwordErrorMessage);
    }

    public String getUsernameInputClass() {
        return ElementUtils.getAttribute(usernameInput, "class");
    }

    public String getPasswordInputClass() {
        return ElementUtils.getAttribute(passwordInput, "class");
    }

    public boolean isSuccessScreenDisplayed() {
        return ElementUtils.isDisplayed(successScreen);
    }

    public String getSuccessHeadingText() {
        return ElementUtils.getText(successHeading);
    }

    public String getSuccessMessageText() {
        return ElementUtils.getText(successMessage);
    }

    public String getLoggedInUsernameText() {
        return ElementUtils.getText(loggedInUsername);
    }
}