package burukeyou.user.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import burukeyou.common.entity.pojo.BasePojo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("ums_roles")
public class UmsRole extends BasePojo implements Serializable {

	private String code;

	private String name;

	private String description;

	@TableField(exist = false)
	private Set<String> resourceIds;
}
