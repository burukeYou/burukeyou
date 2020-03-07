package burukeyou.admin.entity.vo;

import burukeyou.admin.entity.bo.menu.TreeNode;
import burukeyou.admin.entity.pojo.UmsPermission;
import burukeyou.common.core.entity.dto.BaseOutputConverter;
import lombok.Data;

@Data
public class MenuTreeVO extends TreeNode implements BaseOutputConverter<MenuTreeVO, UmsPermission> {

	private String code;

	private String parentId;

	private Integer orderNum;

	//private String path;

	private String type;

	private String href;

	private String icon;

	private String name;

	private String description;

	private Boolean enabled;
}
