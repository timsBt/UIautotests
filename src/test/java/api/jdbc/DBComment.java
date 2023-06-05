package api.jdbc;

import io.qameta.allure.Step;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public final class DBComment {

    private DBComment() {
    }

    /**
     * Объявление переменной (Скрипт для создания комментария в БД).
     */
    private static final String SQL_CREATE_COMMENT = "INSERT INTO wp_comments"
            + " (comment_post_ID, comment_author, comment_author_email,"
            + " comment_date, comment_date_gmt, comment_content) VALUES"
            + " (?, ?, ?, ?, ?, ?)";

    /**
     * Объявление переменной (Скрипт для удаления комментария в БД).
     */
    private static final String SQL_DELETE_COMMENT = "DELETE FROM wp_comments"
            + " WHERE comment_ID = ?";

    /**
     * Объявление переменной (Скрипт для изменения комментария в БД).
     */
    private static final String SQL_UPDATE_COMMENT = "UPDATE wp_comments SET"
            + " comment_content = REPLACE (comment_content, ?, ?)";

    /**
     * Метод создания комментария к посту в БД.
     *
     * @param content текст комментария
     * @param postID  идентификатор поста
     * @return идентификатор комментария
     */
    @Step("Создание комментария")
    public static Integer createComment(final String content,
                                        final int postID) {
        Integer id = null;
        try (Connection connection = DBConnect.getConnection();
             PreparedStatement createComment = connection
                     .prepareStatement(SQL_CREATE_COMMENT,
                             new String[]{"id"})) {
            int index = 1;
            createComment.setInt(index, postID);
            createComment.setString(++index, "Timur.Bekmatov");
            createComment.setString(++index, "tims.bekmat@gmail.com");
            createComment.setTimestamp(++index,
                    Timestamp.valueOf(LocalDateTime.now()));
            createComment.setTimestamp(++index,
                    Timestamp.valueOf(LocalDateTime.now()));
            createComment.setString(++index, content);
            createComment.executeUpdate();
            ResultSet gk = createComment.getGeneratedKeys();
            if (gk.next()) {
                id = gk.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id;
    }

    /**
     * Метод удаления комментария в БД.
     *
     * @param commentId идентификатор комментария
     */
    @Step("Удаление комментария")
    public static void deleteComment(final int commentId) {
        try (Connection connection = DBConnect.getConnection();
             PreparedStatement deleteComment = connection
                     .prepareStatement(SQL_DELETE_COMMENT)) {
            deleteComment.setInt(1, commentId);
            deleteComment.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Метод изменения комментария в БД.
     *
     * @param comment        текс комментария
     * @param replaceComment измененный текст комментария
     */
    @Step("Изменение комментария")
    public static void updateComment(final String comment,
                                     final String replaceComment) {
        try (Connection connection = DBConnect.getConnection();
             PreparedStatement updateComment = connection
                     .prepareStatement(SQL_UPDATE_COMMENT)) {
            updateComment.setString(1, comment);
            updateComment.setString(2, replaceComment);
            updateComment.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
