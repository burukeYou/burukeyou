package burukeyou.user.entity.dto;

import burukeyou.common.core.entity.dto.BaseInputConverter;
import burukeyou.common.core.utils.ValidationGroupRules.UPDATE;
import burukeyou.common.core.utils.ValidationGroupRules.INSERT;
import burukeyou.user.entity.pojo.UmsUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel
@Data
public class UserDto implements BaseInputConverter<UmsUser> {

    @ApiModelProperty(value = "用户id,存在代表修改，否则添加")
    @NotNull(message = "更新时id不能为空",groups = UPDATE.class)
    private String id;

    @ApiModelProperty(value = "用户账号(*)")
    @NotBlank(message = "用户账号不能为空",groups = INSERT.class)
    @Length(min = 2, max = 25, message = "用户名长度在2到25个字符",groups = {INSERT.class,UPDATE.class})
    private String username;

    @ApiModelProperty(value = "用户密码(*)")
    @NotBlank(message = "用户密码密码为空",groups = INSERT.class)
    @Length(min = 2, max = 25, message = "密码长度在3到25个字符",groups = {INSERT.class,UPDATE.class})
    private String password;

    @ApiModelProperty(value = "用户昵称(*)")
    @NotBlank(message = "用户昵称不能为空",groups = INSERT.class)
    @Length(min = 2, max = 25, message = "密码长度在3到25个字符",groups = {INSERT.class,UPDATE.class})
    private String nickname;

    @ApiModelProperty(value = "用户手机号")
    private String mobile;

    @ApiModelProperty(value = "用户邮箱")
    private String email;
}
