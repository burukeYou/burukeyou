package burukeyou.boiling.entity.dto;

import burukeyou.boiling.entity.pojo.AmsBoiling;
import burukeyou.common.core.entity.dto.BaseInputConverter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ApiModel("沸点")
public class BoilingDto implements BaseInputConverter<AmsBoiling> {

    @ApiModelProperty(value = "沸点内容")
    @NotNull(message = "沸点内容不能为空")
    private String content;

    @ApiModelProperty(value = "沸点话题ID")
    private String topicId;

    @ApiModelProperty(value = "沸点话题名字")
    private String topicName;

    @ApiModelProperty(value = "沸点是否公开")
    @NotNull(message = "请设置是否公开")
    private boolean ispublic;

    @ApiModelProperty(value = "沸点携带的文件")
    @Max(value = 9,message = "携带文件个数不能超过9个")
    private List<String> imgfiles;


}
