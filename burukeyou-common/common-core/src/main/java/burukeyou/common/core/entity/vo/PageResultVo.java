package burukeyou.common.core.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "restful请求统一的返回类型(带分页)" )
public class PageResultVo<T> {

    @ApiModelProperty(value = "当前页")
    private Object page;

    @ApiModelProperty(value = "总页数")
    private Object totalPage;

    @ApiModelProperty(value = "总记录数")
    private Object count;

    @ApiModelProperty(value = "数据")
    private List<T> data;
}
