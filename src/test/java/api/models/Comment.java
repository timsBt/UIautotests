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
public class Comment {

    /**
     * Объявление переменной (пост).
     */
    private Integer post;

    /**
     * Объявление переменной (текст комментария).
     */
    private String content;

    /**
     * Объявление переменной (идентификатор комментария).
     */
    private Integer id;

    /**
     * Объявление переменной (статус комментария).
     */
    private String status;

}
