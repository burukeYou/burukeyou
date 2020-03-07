package burukeyou.admin.entity.dto;

import burukeyou.admin.entity.pojo.UmsRole;
import burukeyou.common.core.entity.dto.BaseInputConverter;
import burukeyou.common.core.utils.ValidationGroupRules.UPDATE;
import burukeyou.common.core.utils.ValidationGroupRules.INSERT;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@ApiModel("角色")
public class RoleDto implements BaseInputConverter<UmsRole> {

    @ApiModelProperty(value = "新建时不用传，更新时要传")
    @NotNull(message = "更新是id不能为空",groups = UPDATE.class)
    private String id;


    @ApiModelProperty("角色编码")
    @NotBlank(message = "角色编码不能为空",groups = INSERT.class)
    private String code;

    @ApiModelProperty("角色名字")
    @NotBlank(message = "角色名字不能为空",groups = INSERT.class)
    private String name;

    @ApiModelProperty("角色简介")
    private String description;
}
