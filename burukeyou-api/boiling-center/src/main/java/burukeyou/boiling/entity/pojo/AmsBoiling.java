package burukeyou.boiling.entity.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@TableName("ams_boiling")
@AllArgsConstructor
@NoArgsConstructor
public class AmsBoiling  {

	@TableId(type = IdType.ASSIGN_ID)
	private String id;

	private String content;

	private String contentPic;

	private String userId;

	private String userNickname;

	private String userAvatar;

	private String topicId;

	private String topicName;

	private Integer visitsCount;

	private Integer thumbupCount;

	private Integer commentCount;

	private Integer state;

	private Boolean ispublic;

	private String url;

	@TableField(fill = FieldFill.INSERT)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
	private Date createdTime;
}
