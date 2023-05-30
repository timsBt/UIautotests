package api.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static utils.PropertiesUtils.valueProperties;

/**
 * Класс утилит для авторизации.
 */
public final class AuthUtils {

    private AuthUtils() {
    }

    /**
     * Метод для авторизации.
     *
     * @return авторизация
     */
    public static RequestSpecification auth() {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .auth()
                .preemptive()
                .basic(valueProperties("usernameApi"),
                        valueProperties("passwordApi"))
                .baseUri(valueProperties("uri"));
    }
}
