package burukeyou.boiling.entity.vo;

import burukeyou.boiling.entity.pojo.AmsBoiling;
import burukeyou.common.core.entity.dto.BaseOutputConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AmsBoilingVo implements Serializable, BaseOutputConverter<AmsBoilingVo, AmsBoiling> {

	private String id;

	private String userId;

	private String userNickname;

	private String userAvatar;

	private String content;

	private String[] contentPic;

	private String topicId;

	private String topicName;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
	private Date createdTime;

	private Integer visitsCount;

	private Integer thumbupCount;

	private Integer commentCount;

	private String url;

	private boolean isFollow;

	private boolean isThumbup;
}
