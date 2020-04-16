package burukeyou.system.entity.pojo;

import burukeyou.common.dao.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("sys_topic")
public class SysTopic extends BasePojo implements Serializable {

	private String avatar;

	private Long focusCount;

	private Long boilingCount;

	private String description;


}
