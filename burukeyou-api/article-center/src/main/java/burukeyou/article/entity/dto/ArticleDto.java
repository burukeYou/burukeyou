package burukeyou.article.entity.dto;

import burukeyou.article.entity.pojo.AmsArticle;
import burukeyou.common.core.entity.dto.BaseInputConverter;
import burukeyou.common.core.utils.ValidationGroupRules.INSERT;
import burukeyou.common.core.utils.ValidationGroupRules.UPDATE;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@ApiModel
public class ArticleDto implements BaseInputConverter<AmsArticle> {

    @ApiModelProperty(value = "新建时不用传，更新时要传")
    @NotNull(message = "更新是id不能为空",groups = UPDATE.class)
    private String id;

    @ApiModelProperty(value = "文章标题")
    @NotBlank(message = "文章标题不能为空",groups = INSERT.class)
    @Size(max = 50, message = "专栏名称字符长度不能超过 {max}",groups = {INSERT.class,UPDATE.class})
    private String title;

    @ApiModelProperty(value = "文章头像图")
    private String image;

    @ApiModelProperty(value = "收藏夹封面图片文件")
    private MultipartFile backgroundFile;

    @ApiModelProperty(value = "文章描述")
    private String description;

    @ApiModelProperty(value = "文章md5内容")
    @NotBlank(message = "文章内容不能为空",groups = INSERT.class)
    private String content;

    @ApiModelProperty(value = "文章html内容")
    private String htmlContent;

    @ApiModelProperty(value = "专栏id")
    @NotNull(message = "文章所属专栏id不能为空",groups = INSERT.class)
    private String columnId;

    @ApiModelProperty(value = "专栏名字")
    @NotNull(message = "专栏名字不能为空",groups = INSERT.class)
    private String columnName;

    @ApiModelProperty(value = "系统频道id")
    @NotNull(message = "文章所属专栏id不能为空",groups = INSERT.class)
    private String channelId;

    @ApiModelProperty(value = "系统频道名字")
    @NotNull(message = "专栏名字不能为空",groups = INSERT.class)
    private String channelName;

    @ApiModelProperty(value = "是否公开")
    @NotNull(message = "是否公开不能为空",groups = INSERT.class)
    private String ispublic;

    @ApiModelProperty(value = "文章标签列表")
    private List<LabelDto> labels;
}
