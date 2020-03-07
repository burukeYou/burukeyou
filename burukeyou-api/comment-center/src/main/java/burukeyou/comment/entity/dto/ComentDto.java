package burukeyou.comment.entity.dto;

import burukeyou.comment.entity.pojo.AmsComment;
import burukeyou.common.core.entity.dto.BaseInputConverter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@ApiModel("发表的评论")
public class ComentDto implements BaseInputConverter<AmsComment> {


    @ApiModelProperty(value = "评论所属实体id")
    @NotBlank(message = "评论所属实体id不能为空")
    private String parentId;

    @ApiModelProperty(value = "评论所属实体类型")
    @NotNull(message = "评论所属实体类型不能为空")
    private int parentType;

    @ApiModelProperty(value = "评论的内容")
    @NotBlank(message = "评论内容不能为空")
    @Size(message = "评论内容长度超过限制",max = 255)
    private String content;
}
