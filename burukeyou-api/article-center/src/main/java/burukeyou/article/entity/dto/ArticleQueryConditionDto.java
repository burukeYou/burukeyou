package burukeyou.article.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleQueryConditionDto {

    private String title;

    private String userId;

    private String state;

    private int page;

    private int size;
}
