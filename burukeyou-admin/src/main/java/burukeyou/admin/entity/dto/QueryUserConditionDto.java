package burukeyou.admin.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "多条件查找用户条件")
public class QueryUserConditionDto {

    @ApiModelProperty(value = "当前页")
    @NotBlank(message = "查找当前页不能为空")
    private int page;

    @ApiModelProperty(value = "每页显示大小")
    private int size = 5;

    @ApiModelProperty(value = "用户状态是否可用")
    private int enabled;

    @ApiModelProperty(value = "角色类型")
    private String role;

    @ApiModelProperty(value = "搜索条件")
    private String condition;

    @ApiModelProperty(value = "排序字段")
    private String orderField;

    @ApiModelProperty(value = "升序还是降序")
    private String order;

}
