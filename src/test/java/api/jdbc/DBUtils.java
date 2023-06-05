package api.jdbc;

import api.models.Comment;
import api.models.Post;
import io.qameta.allure.Step;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class DBUtils {

    private DBUtils() {
    }

    /**
     * Объявление переменной (Скрипт для запроса поста в БД).
     */
    private static final String SQL_POST = "SELECT * FROM wp_posts WHERE ID = ?"
            + " AND post_title = ? AND post_content = ?";

    /**
     * Объявление переменной (Скрипт для запроса комментария в БД).
     */
    private static final String SQL_COMMENT = "SELECT * FROM wp_comments"
            + " WHERE comment_ID = ? AND comment_content = ?";

    /**
     * Метод запроса поста из БД.
     *
     * @param id      идентификатор поста
     * @param title   заголовок поста
     * @param content текст поста
     * @return результат запроса
     */
    @Step("Получение данных поста из БД")
    public static Post getPostDB(final int id, final String title,
                                 final String content) {
        Post posts = new Post();

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(SQL_POST)) {
            preparedStatement.setString(1, String.valueOf(id));
            preparedStatement.setString(2, title);
            final int paramIndexContent = 3;
            preparedStatement.setString(paramIndexContent, content);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer id1 = resultSet.getInt("id");
                String title1 = resultSet.getString("post_title");
                String content1 = resultSet.getString("post_content");
                String status = resultSet.getString("post_status");
                posts = new Post(id1, title1, content1, status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    /**
     * Метод запроса комментария из БД.
     *
     * @param idComment идентификатор комментария
     * @param content   текст комментария
     * @return результат запроса
     */
    @Step("Получение данных комментария из БД")
    public static Comment getCommentDB(final Integer idComment,
                                       final String content) {
        Comment comment = new Comment();
        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(SQL_COMMENT)) {
            preparedStatement.setString(1, String.valueOf(idComment));
            preparedStatement.setString(2, content);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer postId = resultSet.getInt("comment_post_ID");
                String commentContent = resultSet.getString("comment_content");
                Integer commentId = resultSet.getInt("comment_ID");
                String commentStatus = resultSet.getString("comment_approved");
                comment = new Comment(postId, commentContent,
                        commentId, commentStatus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comment;
    }
}
