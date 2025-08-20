package utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserApiHelper extends BaseTest {

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
