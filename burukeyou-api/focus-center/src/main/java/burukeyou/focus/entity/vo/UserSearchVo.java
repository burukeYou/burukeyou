package burukeyou.focus.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSearchVo  {

    private String id;

    private String username;

    private String nickname;

    private String mobile;

    private String email;

    private String avatar;

    private String qrcode;

    private String description;
}
