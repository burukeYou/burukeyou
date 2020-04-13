package burukeyou.focus.entity.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@TableName("ums_focus")
public class UmsFocus implements Serializable {

	@TableId(type = IdType.ASSIGN_ID)
	private String id;

	private String userId;

	private String userNickname;

	private String userAvatar;

	private String targetId;

	private String targetType;

}
