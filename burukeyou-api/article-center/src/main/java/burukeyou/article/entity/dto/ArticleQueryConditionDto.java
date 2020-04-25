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

    private String loginUserId;

    private String state;

    private String channelId;

    private String columnId;

    private int page;

    private int size;
}
