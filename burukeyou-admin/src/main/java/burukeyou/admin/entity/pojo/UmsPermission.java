package burukeyou.admin.entity.pojo;

import burukeyou.common.dao.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("ums_menu")
public class UmsPermission extends BasePojo implements Serializable {

	private String code;

	private String parentId;

	private Integer orderNum;

	private String path;

	private String type;

	private String href;

	private String icon;

	private String name;

	private String description;

	private Boolean enabled;
}
