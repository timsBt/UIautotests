package api.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.ToString;
import lombok.Getter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Post {

    /**
     * Объявление переменной (идентификатор поста).
     */
    private Integer id;

    /**
     * Объявление переменной (заголовок поста).
     */
    private String title;

    /**
     * Объявление переменной (текст поста).
     */
    private String content;

    /**
     * Объявление переменной (статус поста).
     */
    private String status;

}
