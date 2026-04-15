package base;

import factory.DriverFactory;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.LoggerUtils;

public class BaseTest {

    protected final Logger logger = LoggerUtils.getLogger(this.getClass());

    @BeforeMethod
    public void setUp(ITestResult result) {
        logger.info("========== STARTING TEST: {} ==========", result.getMethod().getMethodName());
        new DriverFactory().initializeDriver();
        logger.info("WebDriver initialized successfully");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        String testName = result.getMethod().getMethodName();

        if (result.getStatus() == ITestResult.FAILURE) {
            logger.error("TEST FAILED: {}", testName);
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            logger.info("TEST PASSED: {}", testName);
        } else if (result.getStatus() == ITestResult.SKIP) {
            logger.warn("TEST SKIPPED: {}", testName);
        }

        try {
            DriverFactory.quitDriver();
            logger.info("WebDriver closed successfully");
        } catch (Exception e) {
            logger.error("Error while closing WebDriver", e);
        }

        logger.info("========== END OF TEST: {} ==========\n", testName);
    }

    protected WebDriver getDriver() {
        return DriverFactory.getDriver();
    }
}