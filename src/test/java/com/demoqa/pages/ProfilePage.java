package com.demoqa.pages;

import com.demoqa.models.LoginResponseModel;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import org.openqa.selenium.Cookie;

public class ProfilePage {
    public ProfilePage openProfileWithCookies (LoginResponseModel loginResponseModel){
        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", loginResponseModel.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", loginResponseModel.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", loginResponseModel.getExpires()));
        return this;
    }

    public void checkUserName(String username){
        open("/profile");
        $("#userName-value").shouldHave(text(username));
    }

    public ProfilePage checkBookIsVisible(String bookName) {
        open("/profile");
        $(".rt-tbody").shouldHave(text(bookName));
        return this;
    }

    public ProfilePage checkBookListIsEmpty(String textValue) {
        open("/profile");
        $(".rt-noData").shouldHave(text(textValue));
        return this;
    }

}
