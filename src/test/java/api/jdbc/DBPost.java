package api.jdbc;

import io.qameta.allure.Step;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.sql.Timestamp;

public final class DBPost {

    private DBPost() {
    }

    /**
     * Объявление переменной (Скрипт для создания поста в БД).
     */
    private static final String SQL_CREATE_POST = "INSERT INTO wp_posts"
            + " (post_author, post_date, post_date_gmt, post_title,"
            + " post_content, post_excerpt, post_status, to_ping, pinged,"
            + " post_modified, post_modified_gmt, post_content_filtered) VALUES"
            + " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    /**
     * Объявление переменной (Скрипт для удаления поста в БД).
     */
    private static final String SQL_DELETE_POST = "DELETE FROM wp_posts"
            + " WHERE ID = ?";

    /**
     * Объявление переменной (Скрипт для изменения поста в БД).
     */
    private static final String SQL_UPDATE_POST = "UPDATE wp_posts SET"
            + " post_title = REPLACE (post_title, ?, ?)";

    /**
     * Метод создания поста в БД.
     *
     * @param title   заголовок поста
     * @param content текст поста
     * @return идентификатор поста
     */
    @Step("Создание поста")
    public static Integer createPost(final String title, final String content) {
        Integer id = null;
        try (Connection connection = DBConnect.getConnection();
             PreparedStatement createPost = connection
                     .prepareStatement(SQL_CREATE_POST,
                             new String[]{"id"})) {
            int index = 1;
            createPost.setInt(index, 1);
            createPost.setTimestamp(++index,
                    Timestamp.valueOf(LocalDateTime.now()));
            createPost.setTimestamp(++index,
                    Timestamp.valueOf(LocalDateTime.now()));
            createPost.setString(++index, title);
            createPost.setString(++index, content);
            createPost.setString(++index, "");
            createPost.setString(++index, "publish");
            createPost.setString(++index, "");
            createPost.setString(++index, "");
            createPost.setTimestamp(++index,
                    Timestamp.valueOf(LocalDateTime.now()));
            createPost.setTimestamp(++index,
                    Timestamp.valueOf(LocalDateTime.now()));
            createPost.setString(++index, "");
            createPost.executeUpdate();
            ResultSet gk = createPost.getGeneratedKeys();
            if (gk.next()) {
                id = gk.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id;
    }

    /**
     * Метод удаления поста в БД.
     *
     * @param postId идентификатор поста
     */
    @Step("Удаление поста")
    public static void deletePost(final int postId) {
        try (Connection connection = DBConnect.getConnection();
             PreparedStatement deletePost = connection
                     .prepareStatement(SQL_DELETE_POST)) {
            deletePost.setInt(1, postId);
            deletePost.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Метод изменения поста в БД.
     *
     * @param title        заголовок поста
     * @param replaceTitle измененный заголовок поста
     */
    @Step("Изменение поста")
    public static void updatePost(final String title,
                                  final String replaceTitle) {
        try (Connection connection = DBConnect.getConnection();
             PreparedStatement updatePost = connection
                     .prepareStatement(SQL_UPDATE_POST)) {
            updatePost.setString(1, title);
            updatePost.setString(2, replaceTitle);
            updatePost.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
