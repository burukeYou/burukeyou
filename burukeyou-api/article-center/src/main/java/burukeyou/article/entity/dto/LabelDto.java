package burukeyou.article.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class LabelDto {

    @ApiModelProperty(value = "标签id")
    private String id;

    @ApiModelProperty(value = "标签名")
    private String name;
}
