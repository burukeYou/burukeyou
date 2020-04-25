package burukeyou.system.entity.pojo;

import burukeyou.common.dao.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@TableName("sys_label")
@AllArgsConstructor
@NoArgsConstructor
public class SysLabel extends BasePojo implements Serializable {

	private String name;

	private String avatar;

	private int focusCount;

	private int articleCount;

	@TableField(exist = false)
	private boolean select;

}
