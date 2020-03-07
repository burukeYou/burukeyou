package burukeyou.admin.service;


import burukeyou.admin.entity.dto.QueryUserConditionDto;
import burukeyou.admin.entity.pojo.UmsAdmin;
import burukeyou.admin.entity.vo.UmsAdminVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Set;

public interface UmsAdminService {

    /**
     *  保存或者更新用户
     * @param umsAdmin
     * @return
     */
    boolean saveOrupdate(UmsAdmin umsAdmin);

    /**
     *      根据id删除用户
     * @param id
     * @return
     */
    boolean deleteById(String id);

    /**
     *      多条件获取用户及其角色列表
     * @param queryUserConditionDto 获取条件
     * @return
     */
    Page<UmsAdminVO> getListByCondition(QueryUserConditionDto queryUserConditionDto);

    /**
     *
     * @param userId 用户id
     * @param roleIdList 分配的角色id列表
     * @return  结果
     */
    boolean setRoleOfUser(String userId, Set<String> roleIdList);
}
