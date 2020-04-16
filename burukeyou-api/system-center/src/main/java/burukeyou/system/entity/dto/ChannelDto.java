package burukeyou.system.entity.dto;

import burukeyou.common.core.entity.dto.BaseInputConverter;
import burukeyou.common.core.utils.ValidationGroupRules;
import burukeyou.system.entity.pojo.SysChannel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel("增加频道参数")
public class ChannelDto implements BaseInputConverter<SysChannel> {

    @ApiModelProperty(value = "新建时不用传，更新时要传")
    @NotNull(message = "更新是id不能为空",groups = ValidationGroupRules.UPDATE.class)
    private String id;

    @ApiModelProperty(value = "频道名字")
    @NotBlank(message = "创建的频道名称不能为空",groups = ValidationGroupRules.INSERT.class)
    private String name;

}
