package burukeyou.admin.service;


import burukeyou.admin.entity.pojo.UmsRole;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface UmsRoleService  extends IService<UmsRole> {
    /**
     *      查找用户的所有角色
     * @param userId 用户id
     * @return 角色列表
     */
    List<UmsRole> getByUserId(String userId);

    /**
     *  保存或者更新角色
     * @param umsRole
     * @return
     */
    boolean addOrUpdate(UmsRole umsRole);

    /**
     *  删除角色
     ** @param id 唯一id
     * @return
     */
    boolean deleteRoleById(String id);

    Page<UmsRole> getPage(int page, int size);
}
