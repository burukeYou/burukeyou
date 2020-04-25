package burukeyou.article.entity.vo;

import burukeyou.article.entity.pojo.AmsArticle;
import burukeyou.common.core.entity.dto.BaseOutputConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleListlVo implements BaseOutputConverter<ArticleListlVo, AmsArticle> {

    private String id;

    private String title;

    private String image;

    private String description;

    private String userId;

    private String userNickname;

    private String userAvatar;

    private String channelName;

    private int visitsCount;

    private int thumbupCount;

    private int commentCount;

   // @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createdTime;

    private boolean thumbup;
}
