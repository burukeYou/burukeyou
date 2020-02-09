package burukeyou.user.entity.dto;

import burukeyou.common.core.entity.dto.BaseInputConverter;
import burukeyou.user.entity.pojo.UmsUsers;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@ApiModel
@Data
public class UserDto implements BaseInputConverter<UmsUsers> {

    @ApiModelProperty(value = "用户id,存在代表修改，否则添加")
    private String id;

    @ApiModelProperty(value = "用户账号(*)")
    @NotBlank(message = "用户账号不能为空")
    @Length(min = 2, max = 25, message = "用户名长度在2到25个字符")
    private String username;

    @ApiModelProperty(value = "用户密码(*)")
    @NotBlank(message = "用户密码密码为空")
    @Length(min = 3, max = 25, message = "密码长度在3到25个字符")
    private String password;

    @ApiModelProperty(value = "用户昵称(*)")
    @NotBlank(message = "用户昵称不能为空")
    @Length(min = 3, max = 25, message = "密码长度在3到25个字符")
    private String nickname;

    @ApiModelProperty(value = "用户手机号")
    private String mobile;

    @ApiModelProperty(value = "用户邮箱")
    private String email;
}
