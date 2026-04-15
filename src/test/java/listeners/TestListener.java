package listeners;

import io.qameta.allure.Allure;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ScreenshotUtils;

public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        System.out.println("=== Test Suite Started: " + context.getName() + " ===");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("=== Test Suite Finished: " + context.getName() + " ===");
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("STARTED: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        System.out.println("PASSED: " + testName);

        ScreenshotUtils.attachScreenshotToAllure("Screenshot - PASSED - " + testName);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        System.out.println("FAILED: " + testName);

        if (result.getThrowable() != null) {
            Allure.addAttachment(
                    "Failure Reason",
                    "text/plain",
                    result.getThrowable().toString()
            );
        }

        ScreenshotUtils.attachScreenshotToAllure("Screenshot - FAILED - " + testName);
        ScreenshotUtils.attachPageSourceToAllure("Page Source - FAILED - " + testName);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        System.out.println("SKIPPED: " + testName);

        ScreenshotUtils.attachScreenshotToAllure("Screenshot - SKIPPED - " + testName);
    }
}