package utils;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ElementUtils {

    private static final Logger logger = LoggerUtils.getLogger(ElementUtils.class);

    public static void click(By locator) {
        logger.info("Clicking on element: {}", locator);
        WaitUtils.waitForClickable(locator).click();
    }

    public static void type(By locator, String text) {
        logger.info("Typing into element: {} with value: {}", locator, text);
        WebElement element = WaitUtils.waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }

    public static void clear(By locator) {
        logger.info("Clearing element: {}", locator);
        WaitUtils.waitForVisibility(locator).clear();
    }

    public static String getText(By locator) {
        logger.info("Getting text from element: {}", locator);
        return WaitUtils.waitForVisibility(locator).getText();
    }

    public static boolean isDisplayed(By locator) {
        logger.info("Checking if element is displayed: {}", locator);
        try {
            return WaitUtils.waitForVisibility(locator).isDisplayed();
        } catch (Exception e) {
            logger.error("Element not displayed: {}", locator);
            return false;
        }
    }

    public static boolean isEnabled(By locator) {
        logger.info("Checking if element is enabled: {}", locator);
        try {
            return WaitUtils.waitForVisibility(locator).isEnabled();
        } catch (Exception e) {
            logger.error("Element not enabled: {}", locator);
            return false;
        }
    }

    public static String getAttribute(By locator, String attributeName) {
        logger.info("Getting attribute '{}' from element: {}", attributeName, locator);
        return WaitUtils.waitForVisibility(locator).getAttribute(attributeName);
    }
}