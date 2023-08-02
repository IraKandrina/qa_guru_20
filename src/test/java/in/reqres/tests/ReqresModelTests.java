package in.reqres.tests;

import in.reqres.models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static in.reqres.specs.UserSpec.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("remote_api")
public class ReqresModelTests extends TestBase {

    @Test
    @DisplayName("Create user")
    void successfulCreateUserTest() {
        CreateUserModel createData = new CreateUserModel();
        createData.setName("morpheus");
        createData.setJob("leader");

        CreateUserResponseModel createUserResponse = step("Make request", () ->
                given(userRequestSpec)
                        .body(createData)
                        .when()
                        .post("/users")
                        .then()
                        .spec(createUserResponseSpec201)
                        .extract().as(CreateUserResponseModel.class));

        step("Check response", () -> {
                    assertEquals("morpheus", createUserResponse.getName());
                    assertEquals("leader", createUserResponse.getJob());
                }
        );
    }

    @Test
    @DisplayName("Get user data")
    void getUserTest() {

        GetSingleUserResponseModel getSingleUserResponse = step("Make request", () ->
                given(userRequestSpec)
                        .when()
                        .get("/users/2")
                        .then()
                        .spec(getSingleUserResponseSpec200)
                        .extract().as(GetSingleUserResponseModel.class));

        step("Check response", () -> {
                    assertEquals("Janet", getSingleUserResponse.getData().getFirstName());
                    assertEquals("Weaver", getSingleUserResponse.getData().getLastName());
                }
        );
    }

    @Test
    @DisplayName("Update user data")
    void fullUpdateUserTest() {
        CreateUserModel updateData = new CreateUserModel();
        updateData.setName("morpheus");
        updateData.setJob("zion resident");

        UpdateUserResponseModel updateUserResponse = step("Make request", () ->
                given(userRequestSpec)
                        .body(updateData)
                        .when()
                        .put("/users/2")
                        .then()
                        .spec(updateUserResponseSpec200)
                        .extract().as(UpdateUserResponseModel.class));

        step("Check response", () -> {
                    assertEquals("morpheus", updateUserResponse.getName());
                    assertEquals("zion resident", updateUserResponse.getJob());
                }
        );
    }

    @Test
    @DisplayName("Delete user")
    void deleteUserTest() {

        step("Delete user", () ->
                given(userRequestSpec)
                        .when()
                        .delete("/users/2")
                        .then()
                        .spec(deleteUserResponseSpec204));
    }

    @Test
    @DisplayName("Check getting list resources")
    void getListResourceTest() {

        GetUserListResponseModel getUserListResponseModel = step("Make request", () ->
                given(userRequestSpec)
                        .when()
                        .get("/users?page=2")
                        .then()
                        .spec(getUserListResponseSpec200)
                        .extract().as(GetUserListResponseModel.class));

        step("Check response", () -> {
                    assertEquals(12, getUserListResponseModel.getTotal());
                    assertEquals(6, getUserListResponseModel.getData().size());
                    assertEquals(7, getUserListResponseModel.getData().get(0).getId());
                    assertEquals("Michael", getUserListResponseModel.getData().get(0).getFirst_name());
                    assertEquals("Lawson", getUserListResponseModel.getData().get(0).getLast_name());
                    assertEquals(8, getUserListResponseModel.getData().get(1).getId());
                    assertEquals("Lindsay", getUserListResponseModel.getData().get(1).getFirst_name());
                    assertEquals("Ferguson", getUserListResponseModel.getData().get(1).getLast_name());
                }
        );
    }
}
