package burukeyou.boiling.entity.pojo;

import burukeyou.common.dao.pojo.BasePojo;
import lombok.Data;

import java.io.Serializable;

@Data
public class AmsBoiling extends BasePojo implements Serializable {

	private String content;

	private String userId;

	private String userNickname;

	private String userAvatar;

	private String topicId;

	private String topicName;

	private Integer visitsCount;

	private Integer thumbupCount;

	private Integer commentCount;

	private Integer state;

	private Integer ispublic;

	private String url;
}
