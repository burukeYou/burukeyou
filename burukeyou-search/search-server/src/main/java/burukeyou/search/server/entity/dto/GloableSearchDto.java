package burukeyou.search.server.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("通用搜索参数")
public class GloableSearchDto {

    @ApiModelProperty(value = "索引名")
    private String indexName;

    @ApiModelProperty(value = "搜索关键字")
    private String keyword;

    @ApiModelProperty(value = "当前页数")
    private Integer page;

    @ApiModelProperty(value = "每页显示数大小")
    private Integer size;

    @ApiModelProperty(value = "排序字段")
    private String sortField;

    @ApiModelProperty(value = "是否高亮")
    private Boolean isHighlighter;


}
