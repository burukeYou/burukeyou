package burukeYou.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@ApiModel
public class LoginDto {
    @ApiModelProperty(value = "用户账号")
    @Length(min = 2, max = 25, message = "用户名长度在2到25个字符")
    private String username;

    @ApiModelProperty(value = "用户密码")
    @Length(min = 3, max = 25, message = "密码长度在3到25个字符")
    private String password;
}
