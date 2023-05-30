package api.tests;

import api.jdbc.DBUtils;
import api.models.Comment;
import api.steps.PostStep;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Severity;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static api.utils.AuthUtils.auth;
import static utils.PropertiesUtils.valueProperties;

/**
 * Тесты работы с постами.
 */
@Epic(value = "API Tests")
public class CommentTest {

    /**
     * Объявление переменной (Комментарий).
     */
    private final String contentComment = "Новый комментарий";

    /**
     * Объявление переменной (Идентификатор комментария).
     */
    private int commentId;

    /**
     * Объявление переменной (Идентификатор поста).
     */
    private int postId;

    /**
     * Создание поста и комментария к нему.
     */
    @BeforeMethod
    public void createPostsAndComments() {
        Response responsePost = PostStep.createPost("Новый пост",
                "Тут Создание поста", "publish");
        postId = responsePost.jsonPath().getInt("id");
        Comment comment = Comment.builder().post(postId).content(contentComment)
                .build();
        Response responseComment = auth()
                .body(comment)
                .when()
                .post(valueProperties("uri") + "comments/")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().response();
        commentId = responseComment.jsonPath().getInt("id");
    }

    /**
     * Тест создания комментария.
     */
    @Test(description = "Тест создания комментария")
    @Feature(value = "Работа с комментариями")
    @Severity(value = SeverityLevel.NORMAL)
    @Description("Создание комментария")
    public void createCommentTest() {
        Comment comment = DBUtils.getCommentDB(commentId, contentComment);
        Assert.assertEquals(comment.getContent(), contentComment);
    }

    /**
     * Тест изменения комментария.
     */
    @Test(description = "Тест изменения комментария")
    @Feature(value = "Работа с комментариями")
    @Severity(value = SeverityLevel.NORMAL)
    @Description("Изменение комментария")
    public void updateCommentTest() {
        String contentCommentReplace = "Измененный комментарий";
        Comment comment = Comment.builder().id(commentId)
                .content(contentCommentReplace).build();
        auth()
                .body(comment)
                .when()
                .post(valueProperties("uri") + "comments/" + commentId)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract();
        Comment commentDb = DBUtils.getCommentDB(commentId,
                contentCommentReplace);
        Assert.assertEquals(commentDb.getContent(), contentCommentReplace);
    }

    /**
     * Тест удаления комментария.
     */
    @Test(description = "Тест удаления комментария")
    @Feature(value = "Работа с комментариями")
    @Severity(value = SeverityLevel.NORMAL)
    @Description("Удаление комментария")
    public void deleteCommentTest() {
        auth()
                .when()
                .delete(valueProperties("uri") + "comments/" + commentId)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract();
        Comment comment = DBUtils.getCommentDB(commentId, contentComment);
        Assert.assertEquals(comment.getContent(), contentComment);
        Assert.assertEquals(comment.getStatus(), "trash");
    }

    /**
     * Удаление поста.
     */
    @AfterMethod
    public void deletePosts() {
        PostStep.deletePost(postId);
    }
}
