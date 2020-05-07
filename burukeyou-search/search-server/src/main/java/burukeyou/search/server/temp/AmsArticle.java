package burukeyou.search.server.temp;

import burukeyou.common.dao.pojo.BasePojo;
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

	private String channelId;

	private String channelName;

	private String labels;

	private Boolean ispublic;

	private Boolean istop;

	private int visitsCount;

	private int thumbupCount;

	private int commentCount;

	private Integer state;

	private String url;
}
