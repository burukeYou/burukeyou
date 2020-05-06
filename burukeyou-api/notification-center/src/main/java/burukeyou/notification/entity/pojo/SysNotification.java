package burukeyou.notification.entity.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@TableName(value = "sys_notification")
public class SysNotification  implements Serializable {

	@TableId(type = IdType.ASSIGN_ID)
	private String id;

	private String content;

	private String acceptId;

	private String type;

	private boolean status; // this value no invalid when type equals system

	private LocalDateTime createdTime;

}
