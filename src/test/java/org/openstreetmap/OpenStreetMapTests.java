package org.openstreetmap;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;

@Disabled
public class OpenStreetMapTests extends TestBase{

    @ValueSource (strings = {"GPS-треки", "Сообщества"})
    @ParameterizedTest(name = "Отображение заголовка при выборе кнопки {0}")
    void pageHeaderValues(String value) {
        open(baseUrl);
        $$(".nav-item a").findBy(text(value)).click();
        $("#content h1").shouldHave(text(value));
    }

    @CsvSource({
            "Сообщества, Местные отделения",
            "Авторские права, Как сослаться на OpenStreetMap",
            "Дневники участников, Недавние записи в дневнике"
    })

    @ParameterizedTest (name = "Отображение подзаголовка {1} при выборе кнопки {0}")
    void pageSubHeaderValues(String buttonValue, String subHeaders) {
        open(baseUrl);
        $$(".nav-item a").findBy(text(buttonValue)).click();
        $("#content").shouldHave(text(subHeaders));
    }

    static Stream<Arguments> addressValues() {
        return Stream.of(
                Arguments.of("Санкт-Петербург, Дворцовая 1", List.of("Церковь иконы Божией " +
                        "Матери \"Знамение\", 1, Дворцовая улица, София, Пушкин, Санкт-Петербург, " +
                        "Северо-Западный федеральный округ, 196601, Россия", "Место захоронения 1, " +
                        "Дворцовая улица, София, Пушкин, Санкт-Петербург, Северо-Западный федеральный округ," +
                        " 196601, Россия", "Здание 5/1, Дворцовая улица, Пушкин, Санкт-Петербург, Северо-Западный " +
                        "федеральный округ, 196601, Россия")),
                Arguments.of("Санкт-Петербург, Дворцовая 10", List.of("Главный штаб, 10," +
                        " Дворцовая площадь, Дворцовый округ, Санкт-Петербург, Северо-Западный федеральный округ," +
                        " 191186, Россия", "Здание 10, " +
                        "Дворцовая набережная, Дворцовый округ, Санкт-Петербург, Северо-Западный федеральный округ, " +
                        "191186, Россия", "Квартиры 10, Дворцовая улица, Пушкин, Санкт-Петербург, " +
                        "Северо-Западный федеральный округ, 196601, Россия"))
        );
    }

    @MethodSource
    @ParameterizedTest
    void addressValues(String address, List<String> addressValues){
        open(baseUrl);
        $("#sidebar input").setValue(address).pressEnter();
        $$(".list-group-item").should(texts(addressValues));
    }
}
