package burukeyou.admin.service;

import burukeyou.admin.entity.pojo.UmsRolePermission;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UmsRolePermissionService extends IService<UmsRolePermission> {

    /**
     *  动态根据 参数删除关系
     * @param roleId
     * @param permissionId
     * @return
     */
    boolean deleteByRoleIdPermissionId(String roleId,String permissionId);


    /**
     *      设置角色的权限
     */
    boolean saveRolePermission(String roleId,String resourceId);

}
