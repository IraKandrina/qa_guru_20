package hw11.lecture;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class StepsTest {

    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int ISSUE = 80;

    @Test
    public void testLambdaStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open("https://github.com/");
        });

        step("Ищем репозиторий" + REPOSITORY, () -> {
            $(".search-input").click();
            $(By.name("query-builder-test")).click();
            $(By.name("query-builder-test")).sendKeys(REPOSITORY);
            $(By.name("query-builder-test")).pressEnter();
        });

        step("Кликаем по ссылке репозитория" + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });

        step("Открываем таб Issues", () -> {
            $("#issues-tab").click();
        });

        step("Проверяем наличие Issue с номером" + ISSUE, () -> {
            $(withText("#" + ISSUE)).should(exist);
        });
    }

    @Test
    public void testAnnotatedStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        WebStepsLecture steps = new WebStepsLecture();
        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnrRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber("#" + ISSUE);
    }

}
