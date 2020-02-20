package burukeyou.article.entity.vo;

import burukeyou.article.entity.pojo.AmsArticle;
import burukeyou.common.core.entity.dto.BaseOutputConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ArticleListlVo implements BaseOutputConverter<ArticleListlVo, AmsArticle> {

    private String id;

    private String title;

    private String description;

    private String userId;

    private String userNickname;

    private String userAvatar;

    private Long visitsCount;

    private Long thumbupCount;

    private Long commentCount;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createdTime;

}
