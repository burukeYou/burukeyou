package burukeyou.system.entity.dto;

import burukeyou.common.core.entity.dto.BaseInputConverter;
import burukeyou.common.core.utils.ValidationGroupRules;
import burukeyou.system.entity.pojo.SysLabel;
import burukeyou.system.entity.pojo.SysTopic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel("增加话题参数")
public class LabelDto implements BaseInputConverter<SysLabel> {

    @ApiModelProperty(value = "新建时不用传，更新时要传")
    @NotNull(message = "更新是id不能为空",groups = ValidationGroupRules.UPDATE.class)
    private String id;

    @ApiModelProperty(value = "话题名字")
    @NotBlank(message = "创建的话题名字不能为空",groups = ValidationGroupRules.INSERT.class)
    private String name;

    @ApiModelProperty(value = "话题头像")
    private MultipartFile topicAvatar;

    @ApiModelProperty(value = "话题简介")
    private String description;
}
