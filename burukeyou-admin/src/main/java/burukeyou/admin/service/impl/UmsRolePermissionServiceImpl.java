package burukeyou.admin.service.impl;

import burukeyou.admin.entity.pojo.UmsRolePermission;
import burukeyou.admin.mapper.UmsRolePermissionMapper;
import burukeyou.admin.service.UmsRolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UmsRolePermissionServiceImpl extends ServiceImpl<UmsRolePermissionMapper, UmsRolePermission> implements UmsRolePermissionService {


    @Override
    public boolean deleteByRoleIdPermissionId(String roleId, String permissionId) {
        return baseMapper.deleteByRoleIdResourceId(roleId,permissionId);
    }

    @Override
    public boolean saveRolePermission(String roleId, String permissionId) {
        return super.save(new UmsRolePermission(roleId,permissionId));
    }
}
