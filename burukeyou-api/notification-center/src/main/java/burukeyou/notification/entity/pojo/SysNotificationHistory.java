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
@TableName(value = "sys_notification_history")
public class SysNotificationHistory  implements Serializable {

	@TableId(type = IdType.NONE)
	private String notificationId;

	@TableId(type = IdType.NONE)
	private String userId;

	private LocalDateTime createTime;


}
