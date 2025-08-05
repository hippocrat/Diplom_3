package utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserApiHelper {

    public static String getToken(String email, String password) {
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .body(Map.of("email", email, "password", password))
                        .when()
                        .post("https://stellarburgers.nomoreparties.site/api/auth/login");

        String token = response.jsonPath().getString("accessToken");
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return null;
    }

    public static void deleteUser(String email, String password) {
        String token = getToken(email, password);
        if (token != null) {
            given()
                    .auth().oauth2(token)
                    .when()
                    .delete("https://stellarburgers.nomoreparties.site/api/auth/user")
                    .then()
                    .statusCode(202);
        }
    }
}
