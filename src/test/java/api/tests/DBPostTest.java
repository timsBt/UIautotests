package api.tests;

import api.jdbc.DBPost;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static api.utils.AuthUtils.auth;
import static org.hamcrest.Matchers.equalTo;
import static utils.PropertiesUtils.valueProperties;

/**
 * Тесты работы с постами в БД.
 */
@Epic(value = "API Tests")
public class DBPostTest {

    /**
     * Объявление переменной (Заголовок поста).
     */
    private final String titlePost = "Новый пост";

    /**
     * Объявление переменной (Идентификатор поста).
     */
    private int postId;

    /**
     * Создание поста.
     */
    @BeforeMethod
    public void createDBPost() {
        postId = DBPost.createPost(titlePost, "Это Создание поста");
    }

    /**
     * Тест создания поста.
     */
    @Test(description = "Тест создания поста в БД")
    @Feature(value = "Работа с постами в БД")
    @Severity(value = SeverityLevel.NORMAL)
    @Description("Создание поста в БД")
    public void createDBPostTest() {
        auth()
                .when()
                .get(valueProperties("uri") + "posts/" + postId)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("id", equalTo(postId))
                .body("title.rendered", equalTo(titlePost));
        DBPost.deletePost(postId);
    }

    /**
     * Тест изменения поста.
     */
    @Test(description = "Тест изменения поста в БД")
    @Feature(value = "Работа с постами в БД")
    @Severity(value = SeverityLevel.NORMAL)
    @Description("Изменение поста в БД")
    public void updateDBPostTest() {
        DBPost.updatePost(titlePost, "Измененный пост");
        auth()
                .when()
                .get(valueProperties("uri") + "posts/" + postId)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("id", equalTo(postId))
                .body("title.rendered", equalTo("Измененный пост"));
        DBPost.deletePost(postId);
    }

    /**
     * Тест удаления поста.
     */
    @Test(description = "Тест удаления поста в БД")
    @Feature(value = "Работа с постами в БД")
    @Severity(value = SeverityLevel.NORMAL)
    @Description("Удаление поста в БД")
    public void deleteDBPostTest() {
        final int status = 404;
        DBPost.deletePost(postId);
        auth()
                .when()
                .get(valueProperties("uri") + "posts/" + postId)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .body("id", equalTo(null))
                .body("code", equalTo("rest_post_invalid_id"))
                .body("message", equalTo("Неверный ID записи."))
                .body("data.status", equalTo(status));
    }
}
