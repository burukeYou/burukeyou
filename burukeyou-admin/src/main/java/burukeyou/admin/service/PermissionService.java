package burukeyou.admin.service;

import burukeyou.admin.entity.pojo.UmsPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface PermissionService extends IService<UmsPermission> {
    /**
     *
     * @param userId 用户id
     * @return 用户拥有的菜单列表
     */
    List<UmsPermission> getPermissionByUserId(String userId,String type);


    /**
     *
     * @param roleId
     * @param type
     * @return
     */
    List<UmsPermission> getPermissionByRoleId(String roleId,String type);

    /**
     *
     * @param id
     * @return
     */
    boolean deleteById(String id);

    /**
     *     给角色分配权限
     * @param roleId
     * @param permissionIds
     * @return
     */
    boolean setPermissionOfRole(String roleId, List<String>  permissionIds);
}
