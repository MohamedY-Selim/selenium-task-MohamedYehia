package utils;

import factory.DriverFactory;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

public class ScreenshotUtils {

    public static byte[] takeScreenshotAsBytes() {
        WebDriver driver = DriverFactory.getDriver();

        if (driver == null) {
            return new byte[0];
        }

        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public static void attachScreenshotToAllure(String attachmentName) {
        byte[] screenshot = takeScreenshotAsBytes();

        if (screenshot.length > 0) {
            Allure.addAttachment(
                    attachmentName,
                    "image/png",
                    new ByteArrayInputStream(screenshot),
                    ".png"
            );
        }
    }

    public static void attachPageSourceToAllure(String attachmentName) {
        WebDriver driver = DriverFactory.getDriver();

        if (driver != null) {
            Allure.addAttachment(attachmentName, "text/html", driver.getPageSource(), ".html");
        }
    }
}