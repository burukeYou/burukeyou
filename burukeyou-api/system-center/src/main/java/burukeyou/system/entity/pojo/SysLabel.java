package burukeyou.system.entity.pojo;

import burukeyou.common.dao.pojo.BasePojo;
import lombok.Data;

import java.io.Serializable;

@Data
public class SysLabel extends BasePojo implements Serializable {

	private String name;

	private String avatar;

	private int focusCount;

	private int articleCount;

}
