package hw11;

import com.codeborne.selenide.logevents.SelenideLogger;
import hw11.lecture.WebSteps;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
public class StepsTest {

    private static final String REPOSITORY = "allure-examples/allure-mocha-example";
    private static final int ISSUE = 129;

    @Test
    public void testAnnotatedStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        WebSteps steps = new WebSteps();
        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnrRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(ISSUE);
    }

}
