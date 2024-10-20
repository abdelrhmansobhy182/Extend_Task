package apis;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.LoginRequestResponse;
import models.User;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class UserAPIs {

    private static final String BASE_URI = "/users";
    private static final String LOGIN_URI = "/login";

    private static RequestSpecification givenRequest() {
        return given()
                .contentType(ContentType.JSON);
    }

    public static Response createUser(User user) {
        return givenRequest()
                .log().all()
                .body(user)
                .when()
                .post(BASE_URI);
    }

    public static Response getUsers(int page) throws IOException {
        return given()
                .queryParam("page", page)
                .when()
                .get(BASE_URI);
    }

    public static Response getUserById(int id) {
        return given()
                .pathParam("id", id)
                .when()
                .get(BASE_URI + "/{id}");
    }

    public static Response updateUser(String id, User user) {
        return givenRequest()
                .pathParam("id", id)
                .body(user)
                .when()
                .put(BASE_URI + "/{id}");
    }

    public static Response deleteUser(int id) {
        return given()
                .pathParam("id", id)
                .when()
                .delete(BASE_URI + "/{id}");
    }

    public static Response login(LoginRequestResponse user) {
        return givenRequest()
                .log().all()
                .body(user)
                .when()
                .post(LOGIN_URI)
                .then()
                .log().all()
                .extract().response();
    }
}
