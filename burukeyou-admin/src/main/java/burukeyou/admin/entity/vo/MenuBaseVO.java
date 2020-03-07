package burukeyou.admin.entity.vo;

import burukeyou.admin.entity.bo.menu.BaseMenu;
import burukeyou.admin.entity.pojo.UmsPermission;
import burukeyou.common.core.entity.dto.BaseOutputConverter;
import lombok.Data;

import java.util.Date;

@Data
public class MenuBaseVO extends BaseMenu implements BaseOutputConverter<MenuBaseVO, UmsPermission> {

    private String code;

    private String name;

    private Boolean enabled;

    private boolean checked = false;  //当前角色是否该权限

    private boolean open = true;  //当前角色是否该权限

}
