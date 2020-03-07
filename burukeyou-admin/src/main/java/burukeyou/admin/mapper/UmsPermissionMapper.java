package burukeyou.admin.mapper;

import burukeyou.admin.entity.pojo.UmsPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UmsPermissionMapper extends BaseMapper<UmsPermission> {

    /**
     *    动态根据用户id和权限类型获取权限列表
     * @param userId
     * @return
     */
    List<UmsPermission> getAllPermissionByUserId(@Param("userId") String userId, @Param("type") String type);


    /**
     *      动态根据角色id和权限类型获得权限列表
     * @param roleId 角色id
     * @param type  权限类型
     * @return
     */
    List<UmsPermission> getAllPermissionByRoleId(@Param("roleId") String roleId,@Param("type") String type);
}
