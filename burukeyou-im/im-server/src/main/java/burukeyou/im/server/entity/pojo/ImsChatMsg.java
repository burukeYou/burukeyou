package burukeyou.im.server.entity.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("ims_chat_msg")
public class ImsChatMsg  implements Serializable {

	@TableId(type = IdType.ASSIGN_ID)
	private String id;

	private String type;

	private String sendUserId;

	private String sendUserNickname;

	private String sendUserAvatar;

	private String acceptUserId;

	private String msg;

	private String msgType;

	private String url;

	private String signFlag;

	private LocalDateTime createTime;

}
