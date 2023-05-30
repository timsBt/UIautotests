package api.tests;

import api.jdbc.DBUtils;
import api.models.Post;
import api.steps.PostStep;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Severity;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static api.utils.AuthUtils.auth;
import static utils.PropertiesUtils.valueProperties;

/**
 * Тесты работы с постами.
 */
@Epic(value = "API Tests")
public class PostTest {

    /**
     * Объявление переменной (Ответ с сервера).
     */
    private Response response;

    /**
     * Объявление переменной (Идентификатор поста).
     */
    private int postId;

    /**
     * Объявление переменной (Заголовок поста).
     */
    private final String title = "Новый пост";

    /**
     * Объявление переменной (Контент поста).
     */
    private final String content = "Тут Создание поста";

    /**
     * Создание поста.
     */
    @BeforeMethod
    public void createPost() {
        response = PostStep.createPost(title, content, "publish");
        postId = response.jsonPath().getInt("id");
    }

    /**
     * Тест создания поста.
     */
    @Test(description = "Тест создания поста")
    @Feature(value = "Работа с постами")
    @Severity(value = SeverityLevel.NORMAL)
    @Description("Создание поста")
    public void createPostTest() {
        Post posts = DBUtils.getPostDB(postId,
                title, content);
        Assert.assertEquals(posts.getTitle(), title);
        Assert.assertEquals(posts.getContent(), content);
        PostStep.deletePost(postId);
    }

    /**
     * Тест изменения поста.
     */
    @Test(description = "Тест изменения поста")
    @Feature(value = "Работа с постами")
    @Severity(value = SeverityLevel.NORMAL)
    @Description("Изменение поста")
    public void updatePostTest() {
        String titleReplace = "Измененный пост";
        String contentReplace = "Тут Изменение поста";
        Post post = Post.builder().id(postId).title(titleReplace)
                .content(contentReplace).build();
        auth()
                .body(post)
                .when()
                .post(valueProperties("uri") + "posts/" + postId)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract();
        Post posts = DBUtils.getPostDB(postId, titleReplace, contentReplace);
        Assert.assertEquals(posts.getTitle(), titleReplace);
        Assert.assertEquals(posts.getContent(), contentReplace);
        PostStep.deletePost(postId);
    }

    /**
     * Тест удаления поста.
     */
    @Test(description = "Тест удаления поста")
    @Feature(value = "Работа с постами")
    @Severity(value = SeverityLevel.NORMAL)
    @Description("Удаление поста")
    public void deletePostTest() {
        response = PostStep.deletePost(postId);
        Post posts = DBUtils.getPostDB(postId, title, content);
        Assert.assertEquals(posts.getTitle(), title);
        Assert.assertEquals(posts.getContent(), content);
        Assert.assertEquals(posts.getStatus(), "trash");
    }
}
