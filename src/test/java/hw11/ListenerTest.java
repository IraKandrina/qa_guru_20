package hw11;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class ListenerTest {

    @Test
    public void testIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com/");

        $(".search-input").click();
        $(By.name("query-builder-test")).click();
        $(By.name("query-builder-test")).sendKeys("allure-examples/allure-mocha-example");
        $(By.name("query-builder-test")).pressEnter();

        $(linkText("allure-examples/allure-mocha-example")).click();
        $("#issues-tab").click();
        $(withText("129")).should(exist);

    }
}
