package burukeyou.system.entity.vo;

import burukeyou.common.core.entity.dto.BaseOutputConverter;
import burukeyou.system.entity.pojo.SysLabel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysLabelVo implements BaseOutputConverter<SysLabelVo, SysLabel> {

	private String id;

	private String name;

	private String avatar;

	private int focusCount;

	private int articleCount;

	private boolean isFollow;
}
