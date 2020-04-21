package burukeyou.user.entity.vo;

import burukeyou.common.core.entity.dto.BaseOutputConverter;
import burukeyou.user.entity.pojo.UmsUser;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel
public class UserInfoVo implements BaseOutputConverter<UserInfoVo, UmsUser> {

    private String nickname;

    private String avatar;

    private String description;
}
