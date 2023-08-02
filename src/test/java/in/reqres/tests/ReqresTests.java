package in.reqres.tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

public class ReqresTests extends TestBase {

    @Test
    void createUserTest(){
        String data = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";

        given()
                .log().all()
                .contentType(JSON)
                .body(data)
                .when()
                .post("/users")
                .then()
                .log().all()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"));
    }

    @Test
    void getUserTest(){
        given()
                .log().all()
                .when()
                .get("/users/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("data.id", is(2))
                .body("data.first_name", is("Janet"))
                .body("data.last_name", is("Weaver"));
    }

    @Test
    void fullUpdateUserTest(){
        String data = "{ \"name\": \"morpheus\", \"job\": \"zion resident\" }";
        given()
                .log().all()
                .contentType(JSON)
                .body(data)
                .when()
                .put("/users/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("name", is("morpheus"))
                .body("job", is("zion resident"));
    }

    @Test
    void partialUpdateUserTest(){
        String data = "{ \"name\": \"morpheus\", \"job\": \"zion president\" }";
        given()
                .log().all()
                .contentType(JSON)
                .body(data)
                .when()
                .patch("/users/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("name", is("morpheus"))
                .body("job", is("zion president"));
    }

    @Test
    void deleteUserTest(){
        given()
                .log().all()
                .when()
                .delete("/users/2")
                .then()
                .log().all()
                .log().status()
                .log().body()
                .statusCode(204);
    }


}

