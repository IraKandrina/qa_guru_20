package hw11.lecture;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebStepsLecture {

    @Step("Открываем главную страницу")
    public void openMainPage(){
        open("https://github.com/");
    }

    @Step("Ищем репозиторий {repo}")
    public void searchForRepository(String repo){
        $(".search-input").click();
        $(By.name("query-builder-test")).click();
        $(By.name("query-builder-test")).sendKeys(repo);
        $(By.name("query-builder-test")).pressEnter();
    }

    @Step("Кликаем по ссылке репозитория {repo}")
    public void clickOnrRepositoryLink(String repo){
        $(linkText(repo)).click();
    }

    @Step("Открываем таб Issues")
    public void openIssuesTab(){
        $("#issues-tab").click();
    }

    @Step("Проверяем наличие Issue с номером {issue}")
    public void shouldSeeIssueWithNumber(String issue){
        $(withText(issue)).should(exist);
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot(){
        return ((TakesScreenshot)WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
