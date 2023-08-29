package com.demoqa.tests;

import com.demoqa.api.AuthorizationApi;
import com.demoqa.models.LoginResponseModel;
import com.demoqa.pages.ProfilePage;
import org.junit.jupiter.api.Test;

import static com.demoqa.tests.TestData.credentials;

public class LoginTests extends TestBase {
    ProfilePage profilePage = new ProfilePage();
    AuthorizationApi authorizationApi = new AuthorizationApi();
    @Test
    void successfulLoginTest(){
        LoginResponseModel loginResponse = authorizationApi.login(credentials);
        profilePage.openProfileWithCookies(loginResponse);

        profilePage.checkUserName(credentials.getUserName());
    }
}
