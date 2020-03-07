package burukeyou.admin.service;

import burukeyou.admin.entity.pojo.UmsAdminRole;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UmsAdminRoleService extends IService<UmsAdminRole> {

    /**
     *  动态根据用户id，角色id删除关系
     * @param userId
     * @param roleId
     * @return  影响行数
     */
    int deleteByUserIdRoleId(String userId,String roleId);
}
