package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.AllureUtils;
import utils.JsonUtils;

public class LoginTest extends BaseTest {

    @Test(description = "Verify successful login with valid credentials")
    public void validLoginTest() {
        String username = JsonUtils.getData("validUser", "username");
        String password = JsonUtils.getData("validUser", "password");

        logger.info("Running valid login test");

        LoginPage loginPage = new LoginPage();

        loginPage.load().login(username, password);

        AllureUtils.logStep("Assert success screen is displayed");
        Assert.assertTrue(
                loginPage.isSuccessScreenDisplayed(),
                "Success screen should be displayed after valid login"
        );

        AllureUtils.logStep("Assert success heading text");
        Assert.assertEquals(
                loginPage.getSuccessHeadingText(),
                "You're logged in successfully!"
        );

        AllureUtils.logStep("Assert welcome message contains username");
        Assert.assertTrue(
                loginPage.getSuccessMessageText().contains(username),
                "Success message should contain the logged in username"
        );

        Assert.assertEquals(
                loginPage.getLoggedInUsernameText(),
                username,
                "Logged in username should match the entered username"
        );
    }

    @Test(description = "Verify error message with invalid credentials")
    public void invalidLoginTest() {
        String username = JsonUtils.getData("invalidUser", "username");
        String password = JsonUtils.getData("invalidUser", "password");

        logger.info("Running invalid login test");

        LoginPage loginPage = new LoginPage();

        loginPage.load().login(username, password);

        AllureUtils.logStep("Assert error message is displayed");
        Assert.assertTrue(
                loginPage.isErrorDisplayed(),
                "Error message should be displayed for invalid login"
        );

        Assert.assertEquals(
                loginPage.getErrorMessage(),
                "Invalid username or password. Please try again."
        );
    }

    @Test(description = "Verify validation messages when fields are empty")
    public void emptyFieldsTest() {
        String username = JsonUtils.getData("emptyUser", "username");
        String password = JsonUtils.getData("emptyUser", "password");

        logger.info("Running empty fields test");

        LoginPage loginPage = new LoginPage();

        loginPage.load().login(username, password);

        AllureUtils.logStep("Assert username validation message");
        Assert.assertTrue(
                loginPage.isUsernameErrorDisplayed(),
                "Username required message should be displayed"
        );

        Assert.assertEquals(
                loginPage.getUsernameErrorMessage(),
                "Username is required."
        );

        AllureUtils.logStep("Assert password validation message");
        Assert.assertTrue(
                loginPage.isPasswordErrorDisplayed(),
                "Password required message should be displayed"
        );

        Assert.assertEquals(
                loginPage.getPasswordErrorMessage(),
                "Password is required."
        );

        AllureUtils.logStep("Assert input error styling");
        Assert.assertTrue(
                loginPage.getUsernameInputClass().contains("input-error"),
                "Username input should have error styling"
        );

        Assert.assertTrue(
                loginPage.getPasswordInputClass().contains("input-error"),
                "Password input should have error styling"
        );
    }
}