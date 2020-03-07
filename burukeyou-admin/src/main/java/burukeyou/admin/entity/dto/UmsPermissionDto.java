package burukeyou.admin.entity.dto;


import burukeyou.admin.entity.pojo.UmsPermission;
import burukeyou.common.core.entity.dto.BaseInputConverter;
import burukeyou.common.core.utils.ValidationGroupRules;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("菜单")
public class UmsPermissionDto implements BaseInputConverter<UmsPermission> {


	@ApiModelProperty(value = "新建时不用传，更新时要传")
	@NotNull(message = "更新是id不能为空",groups = ValidationGroupRules.UPDATE.class)
	private String id;

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
