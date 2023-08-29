package mobile.tests;

import static com.codeborne.selenide.Selenide.$;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Test;

public class SampleTest extends TestBase {

    @Test
    void sampleTest() {
        $(AppiumBy.accessibilityId("Text Button")).click();
    }



}
