package burukeyou.admin.service;


import burukeyou.admin.entity.dto.QueryUserConditionDto;
import burukeyou.admin.entity.dto.UmsAdminDto;
import burukeyou.admin.entity.pojo.UmsAdmin;
import burukeyou.admin.entity.vo.UmsAdminVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

public interface UmsAdminService extends IService<UmsAdmin> {

    /**
     *  保存或者更新用户
     * @param umsAdminDto
     * @return
     */
    boolean saveOrupdate(UmsAdminDto umsAdminDto);

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
    boolean setRoleOfUser(String userId,Set<String> roleIdList);

    /**
     *  根据id查找
     * @param id
     * @return
     */
     UmsAdminVO getOneById(String id);
}
