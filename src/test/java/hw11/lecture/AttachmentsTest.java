package hw11.lecture;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;

public class AttachmentsTest {
    @Test
    public void testLambdaAttachments() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open("https://github.com/");
            attachment("Source", webdriver().driver().source());
        });
    }

    @Test
    public void testAnnotatedAttachments() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        WebStepsLecture steps = new WebStepsLecture();
        steps.openMainPage();
        steps.takeScreenshot();
    }
}
