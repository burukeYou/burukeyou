package burukeyou.system.entity.pojo;

import burukeyou.common.dao.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("sys_topic")
public class SysTopic extends BasePojo implements Serializable {

	private String name;

	private String avatar;

	private Integer focusCount;

	private Integer boilingCount;

	private String description;


}
