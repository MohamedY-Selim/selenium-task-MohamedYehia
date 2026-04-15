package utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;

public class AllureUtils {

    @Step("{stepName}")
    public static void logStep(String stepName) {
    }

    public static void attachText(String name, String content) {
        Allure.addAttachment(name, "text/plain", content);
    }
}