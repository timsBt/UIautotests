package api.steps;

import api.models.Post;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import static api.utils.AuthUtils.auth;
import static utils.PropertiesUtils.valueProperties;

/**
 * Класс для отправки запросов и получения ответов с серверов.
 */
public final class PostStep {

    private PostStep() {
    }

    /**
     * Метод отправки запроса на создание поста.
     *
     * @param title   заголовок поста
     * @param content текст поста
     * @param status  статус поста
     * @return ответ на запрос
     */
    @Step("Создание поста")
    public static Response createPost(final String title, final String content,
                                      final String status) {
        Post post = Post.builder().title(title).content(content).status(status)
                .build();
        return auth()
                .body(post)
                .when()
                .post(valueProperties("uri") + "posts/")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().response();
    }

    /**
     * Метод отправки запроса на удаление поста.
     *
     * @param id идентификатор поста
     * @return ответ на запрос
     */
    @Step("Удаление поста")
    public static Response deletePost(final int id) {
        return auth()
                .when()
                .delete(valueProperties("uri") + "posts/" + id)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
    }
}
