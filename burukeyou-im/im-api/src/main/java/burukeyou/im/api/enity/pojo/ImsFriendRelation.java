package burukeyou.im.api.enity.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("ims_friend_relation")
public class ImsFriendRelation  implements Serializable {

	@TableId(type = IdType.ASSIGN_ID)
	private String id;

	private String userId;

	private String friendId;

	private String friendNickname;

	private String friendAvatar;

	private String firstLetter;

	private Integer status;

	@TableField(fill = FieldFill.INSERT_UPDATE)
	private java.util.Date createTime;

}
