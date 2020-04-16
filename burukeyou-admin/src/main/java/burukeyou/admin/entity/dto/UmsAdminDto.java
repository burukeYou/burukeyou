package burukeyou.admin.entity.dto;

import burukeyou.common.core.utils.ValidationGroupRules.UPDATE;
import burukeyou.common.core.utils.ValidationGroupRules.INSERT;
import burukeyou.admin.entity.pojo.UmsAdmin;
import burukeyou.common.core.entity.dto.BaseInputConverter;
import burukeyou.common.core.utils.ValidationGroupRules;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Data
@ApiModel(value = "后台用户信息")
public class UmsAdminDto implements BaseInputConverter<UmsAdmin> {

    @ApiModelProperty(value = "新建时不用传，更新时要传")
    @NotNull(message = "更新是id不能为空",groups = UPDATE.class)
    private String id;

    @ApiModelProperty(value = "用户账号(*)")
    @NotBlank(message = "用户账号不能为空",groups = INSERT.class)
    @Length(min = 2, max = 25, message = "用户名长度在2到25个字符",groups = {INSERT.class, UPDATE.class})
    private String adminName;

    @ApiModelProperty(value = "用户密码(*)")
    @NotBlank(message = "用户密码密码为空",groups = ValidationGroupRules.INSERT.class)
    @Length(min = 3, max = 25, message = "密码长度在3到25个字符",groups = {INSERT.class, UPDATE.class})
    private String password;

    @ApiModelProperty(value = "用户昵称(*)")
    @NotBlank(message = "用户昵称不能为空",groups = INSERT.class)
    @Length(min = 3, max = 25, message = "密码长度在3到25个字符",groups = {INSERT.class,UPDATE.class})
    private String nickname;

    @ApiModelProperty(value = "用户联系电话")
    @Size(max = 11,groups = {INSERT.class,UPDATE.class})
    private String mobile;

    @ApiModelProperty(value = "用户邮箱")
    private String email;

    @ApiModelProperty(value = "用户头像url地址")
    private String avatar;

    @ApiModelProperty(value = "用户头像文件")
    private MultipartFile avatarFile;

    @ApiModelProperty(value = "用户简介")
    private String description;

    @ApiModelProperty(value = "用户账号是否可用")
    private Boolean enabled;

    @ApiModelProperty(value = "角色列表")
    private List<String> roleIdLits;
}
