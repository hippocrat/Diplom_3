package utils;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import requests.RegistrationRequest;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserApiHelper extends BaseTest {

    @Step("Регистрация пользователя с email: {email}, password: {password}, name: {name}")
    public static Response registerUser(String email, String password, String name) {
        RegistrationRequest registrationRequest = new RegistrationRequest(email, password, name);
        return given()
                .contentType(ContentType.JSON)
                .body(registrationRequest)
                .when()
                .post("/api/auth/register");
    }

    @Step("Аутентификация пользователя с email: {email}, password: {password} и получение токена")
    public static String getToken(String email, String password) {
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .body(Map.of("email", email, "password", password))
                        .when()
                        .post(LOGIN_API);

        String token = response.jsonPath().getString("accessToken");
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return null;
    }

    @Step("Удаление пользователя с email: {email}, password: {password}")
    public static void deleteUser(String email, String password) {
        String token = getToken(email, password);
        if (token != null) {
            given()
                    .auth().oauth2(token)
                    .when()
                    .delete(DELETE_API)
                    .then()
                    .statusCode(202);
        }
    }
}
