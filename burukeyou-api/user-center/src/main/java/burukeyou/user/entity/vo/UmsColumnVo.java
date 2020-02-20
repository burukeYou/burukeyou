package burukeyou.user.entity.vo;

import burukeyou.common.core.entity.dto.BaseOutputConverter;
import burukeyou.user.entity.pojo.UmsColumn;
import lombok.Data;

/**
 * 		前台 显示 Vo
 */
@Data
public class UmsColumnVo implements BaseOutputConverter<UmsColumnVo, UmsColumn> {

    private String name;

    private String summary;

    private String image;

    private Integer article_count;

}
