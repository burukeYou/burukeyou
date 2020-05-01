package burukeyou.im.api.enity.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("ims_friend_request")
public class ImsFriendRequest  implements Serializable {

	@TableId(type = IdType.ASSIGN_ID)
	private String id;

	private String sendUserId;

	private String sendUserNickname;

	private String sendUserAvatar;

	private String acceptUserId;

	private String msg;

	private Integer status;

	private LocalDateTime createTime;

}
