package burukeyou.focus.entity.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@ToString
@TableName("ums_focus")
@AllArgsConstructor
@NoArgsConstructor
public class UmsFocus implements Serializable {

	@TableId(type = IdType.ASSIGN_ID)
	private String id;

	private String userId;

	//private String userNickname;

	//private String userAvatar;

	private String targetId;

	private String targetType;

}
