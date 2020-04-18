package burukeyou.admin.entity.dto;


import burukeyou.admin.entity.pojo.UmsPermission;
import burukeyou.common.core.entity.dto.BaseInputConverter;
import burukeyou.common.core.utils.ValidationGroupRules;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel("菜单")
public class UmsPermissionDto implements BaseInputConverter<UmsPermission> {


	@ApiModelProperty(value = "新建时不用传，更新时要传")
	@NotBlank(message = "更新是id不能为空",groups = ValidationGroupRules.UPDATE.class)
	private String id;

	@ApiModelProperty(value = "菜单code")
	@NotBlank(message = "菜单code不能为空",groups = ValidationGroupRules.INSERT.class)
	private String code;

	@ApiModelProperty(value = "菜单所属父级目录")
	@NotBlank(message = "菜单所属父级目录不能为空",groups = ValidationGroupRules.INSERT.class)
	private String parentId;

	@ApiModelProperty(value = "新建时不用传，更新时要传")
	private Integer orderNum;

	@ApiModelProperty(value = "菜单类型")
	@NotBlank(message = "菜单类型不能为空",groups = ValidationGroupRules.INSERT.class)
	private String type;

	@ApiModelProperty(value = "菜单url")
	private String url;

	@ApiModelProperty(value = "菜单请求方式")
	private String urlMethod;

	@ApiModelProperty(value = "菜单图标")
	private String icon;

	@ApiModelProperty(value = "菜单名字")
	@NotBlank(message = "菜单名字",groups = ValidationGroupRules.INSERT.class)
	private String name;

	@ApiModelProperty(value = "菜单简介")
	private String description;

	@ApiModelProperty(value = "菜单是否禁用")
	private boolean disabled;

	@ApiModelProperty(value = "菜单是否显示")
	private boolean isshow;
}
