package in.reqres.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static in.reqres.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static org.hamcrest.Matchers.notNullValue;

public class UserSpec {
    public static RequestSpecification userRequestSpec = with()
            .log().uri()
            .log().method()
            .log().body()
            .filter(withCustomTemplates())
            .contentType(JSON);

    public static ResponseSpecification createUserResponseSpec201 = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectBody("id", notNullValue())
            .expectBody("createdAt", notNullValue())
            .expectStatusCode(201)
            .expectBody(matchesJsonSchemaInClasspath("schemas/create-user-schema.json"))
            .build();

    public static ResponseSpecification getSingleUserResponseSpec200 = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectBody("support.url", notNullValue())
            .expectBody("support.text", notNullValue())
            .expectStatusCode(200)
            .expectBody(matchesJsonSchemaInClasspath("schemas/get-single-user-schema.json"))
            .build();


    public static ResponseSpecification updateUserResponseSpec200 = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectBody("updatedAt", notNullValue())
            .expectStatusCode(200)
            .expectBody(matchesJsonSchemaInClasspath("schemas/update-user-schema.json"))
            .build();

    public static ResponseSpecification deleteUserResponseSpec204 = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(204)
            .build();

    public static ResponseSpecification getUserListResponseSpec200 = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectBody("page", notNullValue())
            .expectBody("per_page", notNullValue())
            .expectBody("total_pages", notNullValue())
            .expectBody("data", notNullValue())
            .expectBody("support", notNullValue())
            .expectStatusCode(200)
            .expectBody(matchesJsonSchemaInClasspath("schemas/get-list-users-schema.json"))
            .build();
}
