package burukeyou.article.entity.vo;

import burukeyou.article.entity.pojo.AmsArticle;
import burukeyou.common.core.entity.dto.BaseOutputConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ArticleDetailVo implements BaseOutputConverter<ArticleDetailVo, AmsArticle> {

    private String id;

    private String title;

    private String description;

    private String content;

    private String htmlContent;

    private String userId;

    private String userNickname;

    private boolean isFocusUser;

    private String userAvatar;

    private Boolean ispublic;

    private Boolean istop;

    private int visitsCount;

    private int thumbupCount;

    private int commentCount;

    private boolean isLike;

    private boolean isFavorities;

    private String favoritiesId;

    private Integer state;

    private String url;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createdTime;

    private List<String> labels;



}
