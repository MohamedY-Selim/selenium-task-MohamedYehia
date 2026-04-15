package utils;

import factory.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class WaitUtils {

    private static final int DEFAULT_TIMEOUT = 20;
    private static final int POLLING_TIME = 2;

    private static WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    private static FluentWait<WebDriver> getFluentWait(int timeout) {
        return new FluentWait<>(getDriver())
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(POLLING_TIME))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    // Visibility
    public static WebElement waitForVisibility(By locator) {
        return getFluentWait(DEFAULT_TIMEOUT)
                .until(driver -> driver.findElement(locator).isDisplayed()
                        ? driver.findElement(locator)
                        : null);
    }

    // Clickable
    public static WebElement waitForClickable(By locator) {
        return getFluentWait(DEFAULT_TIMEOUT)
                .until(driver -> {
                    WebElement element = driver.findElement(locator);
                    return (element.isDisplayed() && element.isEnabled()) ? element : null;
                });
    }

    // Presence
    public static WebElement waitForPresence(By locator) {
        return getFluentWait(DEFAULT_TIMEOUT)
                .until(driver -> driver.findElement(locator));
    }

    // Invisibility
    public static boolean waitForInvisibility(By locator) {
        return getFluentWait(DEFAULT_TIMEOUT)
                .until(driver -> {
                    try {
                        return !driver.findElement(locator).isDisplayed();
                    } catch (NoSuchElementException e) {
                        return true;
                    }
                });
    }
}