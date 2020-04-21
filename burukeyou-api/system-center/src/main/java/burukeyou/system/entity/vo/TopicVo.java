package burukeyou.system.entity.vo;

import burukeyou.common.core.entity.dto.BaseOutputConverter;
import burukeyou.system.entity.pojo.SysTopic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("sys_topic")
public class TopicVo implements BaseOutputConverter<TopicVo, SysTopic> {

	private String id;

	private String name;

	private String avatar;

	private Integer focusCount;

	private Integer boilingCount;

	private String  description;

	private boolean isFollow;


}
