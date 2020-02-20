package burukeyou.user.entity.vo;

import burukeyou.common.core.entity.dto.BaseOutputConverter;
import burukeyou.user.entity.pojo.UmsFavorites;
import lombok.Data;

@Data
public class UmsFavoritesListVo implements BaseOutputConverter<UmsFavoritesListVo, UmsFavorites> {

    private String name;

    private String background;

    private String userId;

    private String userNickname;

    private Integer type;

    private Integer count;
}
