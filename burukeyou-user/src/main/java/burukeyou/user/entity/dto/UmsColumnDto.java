package burukeyou.user.entity.dto;

import burukeyou.common.core.entity.dto.BaseInputConverter;
import burukeyou.common.core.utils.ValidationGroupRules.INSERT;
import burukeyou.common.core.utils.ValidationGroupRules.UPDATE;
import burukeyou.user.entity.pojo.UmsColumn;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel
@Data
public class UmsColumnDto implements BaseInputConverter<UmsColumn> {

    @ApiModelProperty(value = "新建时不用传，更新时要传")
    @NotNull(message = "更新是id不能为空",groups = UPDATE.class)
    private String id;

    @Size(max = 24, message = "专栏名称字符长度不能超过 {max}",groups = {INSERT.class,UPDATE.class})
    @NotBlank(message = "专栏名称不能为空",groups = INSERT.class)
    @ApiModelProperty(value = "专栏名称" )
    private String name;

    @ApiModelProperty(value = "专栏简介")
    private String summary;

    @ApiModelProperty(value = "专栏配图" )
    private String image;

    @NotNull(message = "是否公开不能为空",groups = INSERT.class)
    @ApiModelProperty(value = "是否公开" )
    private Boolean ispublic;

    @NotNull(message = "是否置顶不能为空")
    @ApiModelProperty(value = "是否置顶" )
    private Boolean istop;
}
