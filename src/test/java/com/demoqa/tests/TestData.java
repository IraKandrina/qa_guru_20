package com.demoqa.tests;

import com.demoqa.models.CredentialsModel;

public class TestData {
    private static final String login = "test123456";
    private static final String password = "Test123456@";
    public static CredentialsModel credentials = new CredentialsModel(login, password);
}
