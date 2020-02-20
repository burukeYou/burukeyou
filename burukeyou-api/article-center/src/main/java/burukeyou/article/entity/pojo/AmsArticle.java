package burukeyou.article.entity.pojo;

import burukeyou.common.core.entity.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;

@Data
@ToString
@Builder
@TableName("ams_article")
@NoArgsConstructor  // must implement
@AllArgsConstructor // must implement
public class AmsArticle extends BasePojo implements Serializable {

	private String title;

	private String image;

	private String description;

	private String content;

	private String htmlContent;

	private String userId;

	private String userNickname;

	private String userAvatar;

	private String columnId;

	private String columnName;

	private String channel_id;

	private String channel_name;

	private Boolean ispublic;

	private Boolean istop;

	private Integer visitsCount;

	private Integer thumbupCount;

	private Integer commentCount;

	private Integer state;

	private String url;
}
