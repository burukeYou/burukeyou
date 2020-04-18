package burukeyou.admin.entity.vo;

import burukeyou.admin.entity.bo.menu.BaseMenu;
import burukeyou.admin.entity.pojo.UmsPermission;
import burukeyou.common.core.entity.dto.BaseOutputConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class MenuBaseVO extends BaseMenu implements BaseOutputConverter<MenuBaseVO, UmsPermission> {

    private String code;

    private String name;

    private boolean disabled;

    private String url;

    private String urlMethod;

    private String description;

    private String type;

    private boolean isshow;

    private String icon;

    private String createdBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createdTime;

    private String updatedBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date updatedTime;


    //private boolean checked = false;  //当前角色是否该权限

   // private boolean open = true;  //当前角色是否该权限

}
