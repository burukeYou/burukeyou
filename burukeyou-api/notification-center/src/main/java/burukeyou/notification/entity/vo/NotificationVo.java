package burukeyou.notification.entity.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationVo implements Serializable {

	private String id;

	private Map<String,?> content;

	private String acceptId;

	private LocalDateTime createdTime;

}
