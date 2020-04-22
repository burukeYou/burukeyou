package burukeyou.focus.entity.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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

	@TableId(type = IdType.INPUT)
	private String userId;

	@TableId(type = IdType.INPUT)
	private String targetId;

	@TableId(type = IdType.INPUT)
	private String targetType;

	@TableField(exist = false)
	private boolean focus;

}
