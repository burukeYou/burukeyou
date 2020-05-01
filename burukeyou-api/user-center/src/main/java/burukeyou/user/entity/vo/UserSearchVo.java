package burukeyou.user.entity.vo;

import burukeyou.common.core.entity.dto.BaseOutputConverter;
import burukeyou.user.entity.pojo.UmsUser;
import lombok.Data;

@Data
public class UserSearchVo implements BaseOutputConverter<UserSearchVo, UmsUser> {

    private String id;

    private String username;

    private String nickname;

    private String mobile;

    private String email;

    private String avatar;

    private String qrcode;

    private String description;
}
