package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
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
    @Step("Open login page")
    public LoginPage load() {
        String url = ConfigUtils.getInstance().getFileUrl("basepath");
        driver.get(url);
        return this;
    }

    @Step("Enter username: {username}")
    public LoginPage enterUsername(String username) {
        ElementUtils.type(usernameInput, username);
        return this;
    }

    @Step("Enter password")
    public LoginPage enterPassword(String password) {
        ElementUtils.type(passwordInput, password);
        return this;
    }

    @Step("Click login button")
    public LoginPage clickLogin() {
        ElementUtils.click(loginButton);
        return this;
    }

    @Step("Login with username: {username}")
    public LoginPage login(String username, String password) {
        return enterUsername(username)
                .enterPassword(password)
                .clickLogin();
    }

    @Step("Get invalid login error message")
    public String getErrorMessage() {
        return ElementUtils.getText(errorMessage);
    }

    @Step("Check if invalid login error message is displayed")
    public boolean isErrorDisplayed() {
        return ElementUtils.isDisplayed(errorMessage);
    }

    @Step("Get username required message")
    public String getUsernameErrorMessage() {
        return ElementUtils.getText(usernameErrorMessage);
    }

    @Step("Get password required message")
    public String getPasswordErrorMessage() {
        return ElementUtils.getText(passwordErrorMessage);
    }

    @Step("Check if username required message is displayed")
    public boolean isUsernameErrorDisplayed() {
        return ElementUtils.isDisplayed(usernameErrorMessage);
    }

    @Step("Check if password required message is displayed")
    public boolean isPasswordErrorDisplayed() {
        return ElementUtils.isDisplayed(passwordErrorMessage);
    }

    @Step("Get username input class attribute")
    public String getUsernameInputClass() {
        return ElementUtils.getAttribute(usernameInput, "class");
    }

    @Step("Get password input class attribute")
    public String getPasswordInputClass() {
        return ElementUtils.getAttribute(passwordInput, "class");
    }

    @Step("Check if success screen is displayed")
    public boolean isSuccessScreenDisplayed() {
        return ElementUtils.isDisplayed(successScreen);
    }

    @Step("Get success heading text")
    public String getSuccessHeadingText() {
        return ElementUtils.getText(successHeading);
    }

    @Step("Get success message text")
    public String getSuccessMessageText() {
        return ElementUtils.getText(successMessage);
    }

    @Step("Get logged in username text")
    public String getLoggedInUsernameText() {
        return ElementUtils.getText(loggedInUsername);
    }
}