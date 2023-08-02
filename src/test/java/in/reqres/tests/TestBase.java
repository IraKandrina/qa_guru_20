package in.reqres.tests;

import org.junit.jupiter.api.BeforeAll;
import io.restassured.RestAssured;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
    }

}

