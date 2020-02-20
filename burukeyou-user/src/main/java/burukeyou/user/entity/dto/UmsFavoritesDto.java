package burukeyou.user.entity.dto;

import burukeyou.common.core.entity.dto.BaseInputConverter;
import burukeyou.common.core.utils.ValidationGroupRules.INSERT;
import burukeyou.common.core.utils.ValidationGroupRules.UPDATE;
import burukeyou.user.entity.pojo.UmsFavorites;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ApiModel
public class UmsFavoritesDto implements BaseInputConverter<UmsFavorites> {

    @ApiModelProperty(value = "新建时不用传，更新时要传")
    @NotNull(message = "更新是id不能为空",groups = UPDATE.class)
    private String id;

    @ApiModelProperty(value = "收藏夹名字")
    @Size(max = 24, message = "专栏名称字符长度不能超过 {max}",groups = {INSERT.class, UPDATE.class})
    @NotBlank(message = "收藏夹名字不能为空",groups = INSERT.class)
    private String name;

    @ApiModelProperty(value = "收藏夹简介")
    private String description;

    @ApiModelProperty(value = "收藏夹封面图片路径")
    private String background;

    @ApiModelProperty(value = "收藏夹封面图片文件")
    private MultipartFile backgroundFile;

    @ApiModelProperty(value = "是否公开")
    @NotNull(message = "设置是否公开不能为空",groups = INSERT.class)
    private Boolean ispublic;

    @NotNull(message = "收藏夹类型不能为空",groups = INSERT.class)
    @ApiModelProperty(value = "收藏夹类型")
    private Integer type;



}
