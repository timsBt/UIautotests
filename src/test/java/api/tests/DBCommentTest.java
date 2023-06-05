package api.tests;

import api.jdbc.DBComment;
import api.jdbc.DBPost;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static api.utils.AuthUtils.auth;
import static org.hamcrest.Matchers.equalTo;
import static utils.PropertiesUtils.valueProperties;

/**
 * Тесты работы с комментариями в БД.
 */
@Epic(value = "API Tests")
public class DBCommentTest {

    /**
     * Объявление переменной (Текст комментария).
     */
    private final String commentContent = "Новый комментарий";

    /**
     * Объявление переменной (Идентификатор поста).
     */
    private int postId;

    /**
     * Объявление переменной (Идентификатор комментария).
     */
    private int commentId;

    /**
     * Создание поста и комментария.
     */
    @BeforeMethod
    public void createDBComment() {
        postId = DBPost.createPost("Новый пост", "Это Создание поста");
        commentId = DBComment.createComment(commentContent, postId);
    }

    /**
     * Тест создания комментария.
     */
    @Test(description = "Тест создания комментария в БД")
    @Feature(value = "Работа с комментариями в БД")
    @Severity(value = SeverityLevel.NORMAL)
    @Description("Создание комментария в БД")
    public void createDBCommentTest() {
        auth()
                .when()
                .get(valueProperties("uri") + "comments/" + commentId)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("id", equalTo(commentId))
                .body("content.rendered", equalTo("<p>" + commentContent
                        + "</p>\n"));
        DBComment.deleteComment(commentId);
    }

    /**
     * Тест изменения комментария.
     */
    @Test(description = "Тест изменения комментария в БД")
    @Feature(value = "Работа с комментариями в БД")
    @Severity(value = SeverityLevel.NORMAL)
    @Description("Изменение комментария в БД")
    public void updateDBCommentTest() {
        DBComment.updateComment(commentContent, "Измененный комментарий");
        auth()
                .when()
                .get(valueProperties("uri") + "comments/" + commentId)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("id", equalTo(commentId))
                .body("content.rendered", equalTo("<p>"
                        + "Измененный комментарий" + "</p>\n"));
        DBComment.deleteComment(commentId);
    }

    /**
     * Тест удаления комментария.
     */
    @Test(description = "Тест удаления комментария в БД")
    @Feature(value = "Работа с комментариями в БД")
    @Severity(value = SeverityLevel.NORMAL)
    @Description("Удаление комментария в БД")
    public void deleteDBCommentTest() {
        final int status = 404;
        DBComment.deleteComment(commentId);
        auth()
                .when()
                .get(valueProperties("uri") + "comments/" + commentId)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .body("id", equalTo(null))
                .body("code", equalTo("rest_comment_invalid_id"))
                .body("message", equalTo("Неверный ID комментария."))
                .body("data.status", equalTo(status));
    }

    /**
     * Удаление поста.
     */
    @AfterMethod
    public void deleteDBPost() {
        DBPost.deletePost(postId);
    }
}
