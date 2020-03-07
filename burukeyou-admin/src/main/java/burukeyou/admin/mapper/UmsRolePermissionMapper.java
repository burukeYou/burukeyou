package burukeyou.admin.mapper;

import burukeyou.admin.entity.pojo.UmsRolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UmsRolePermissionMapper extends BaseMapper<UmsRolePermission> {

    boolean deleteByRoleIdResourceId(String roleId, String permissionId);
}
