package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import utils.JsonUtils;

public class LoginTest extends BaseTest {

    @Test(description = "Verify successful login with valid credentials")
    public void validLoginTest() {
        String username = JsonUtils.getData("validUser", "username");
        String password = JsonUtils.getData("validUser", "password");

        logger.info("Running valid login test");

        LoginPage loginPage = new LoginPage();
        SoftAssert softAssert = new SoftAssert();

        loginPage.load().login(username, password);

        softAssert.assertTrue(
                loginPage.isSuccessScreenDisplayed(),
                "Success screen should be displayed after valid login"
        );

        softAssert.assertEquals(
                loginPage.getSuccessHeadingText(),
                "You're logged in successfully!",
                "Success heading text is incorrect"
        );

        softAssert.assertTrue(
                loginPage.getSuccessMessageText().contains(username),
                "Success message should contain the logged in username"
        );

        softAssert.assertEquals(
                loginPage.getLoggedInUsernameText(),
                username,
                "Logged in username should match the entered username"
        );

        softAssert.assertAll();
    }

    @Test(description = "Verify error message with invalid credentials")
    public void invalidLoginTest() {
        String username = JsonUtils.getData("invalidUser", "username");
        String password = JsonUtils.getData("invalidUser", "password");

        logger.info("Running invalid login test");

        LoginPage loginPage = new LoginPage();

        loginPage.load().login(username, password);

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
        SoftAssert softAssert = new SoftAssert();

        loginPage.load().login(username, password);

        softAssert.assertTrue(
                loginPage.isUsernameErrorDisplayed(),
                "Username required message should be displayed"
        );

        softAssert.assertEquals(
                loginPage.getUsernameErrorMessage(),
                "Username is required.",
                "Username validation message is incorrect"
        );

        softAssert.assertTrue(
                loginPage.isPasswordErrorDisplayed(),
                "Password required message should be displayed"
        );

        softAssert.assertEquals(
                loginPage.getPasswordErrorMessage(),
                "Password is required.",
                "Password validation message is incorrect"
        );

        softAssert.assertTrue(
                loginPage.getUsernameInputClass().contains("input-error"),
                "Username input should have error styling"
        );

        softAssert.assertTrue(
                loginPage.getPasswordInputClass().contains("input-error"),
                "Password input should have error styling"
        );

        softAssert.assertAll();
    }
}