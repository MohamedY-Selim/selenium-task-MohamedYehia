package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebDriver initializeDriver() {

        String browser = System.getProperty("browser", "CHROME").toUpperCase();
        boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        WebDriver webDriver;

        switch (browser) {
            case "CHROME" -> {
                ChromeOptions options = new ChromeOptions();
                if (isHeadless) {
                    options.addArguments("--headless=new");
                    options.addArguments("--window-size=1920,1080");
                }
                webDriver = new ChromeDriver(options);
            }

            case "FIREFOX" -> {
                FirefoxOptions options = new FirefoxOptions();
                if (isHeadless) {
                    options.addArguments("-headless");
                }
                webDriver = new FirefoxDriver(options);
            }

            case "EDGE" -> {
                EdgeOptions options = new EdgeOptions();
                if (isHeadless) {
                    options.addArguments("--headless=new");
                    options.addArguments("--window-size=1920,1080");
                }
                webDriver = new EdgeDriver(options);
            }

            default -> throw new RuntimeException("The Browser is not supported");
        }

        driver.set(webDriver);

        getDriver().manage().window().maximize();

        return getDriver();
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}