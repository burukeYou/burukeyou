package burukeyou.admin.service.impl;

import burukeyou.admin.entity.pojo.UmsPermission;
import burukeyou.admin.entity.pojo.UmsRolePermission;
import burukeyou.admin.mapper.UmsAdminRoleMapper;
import burukeyou.admin.mapper.UmsPermissionMapper;
import burukeyou.admin.service.PermissionService;
import burukeyou.admin.service.UmsRolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl extends ServiceImpl<UmsPermissionMapper, UmsPermission> implements PermissionService {

    @Autowired
    private UmsRolePermissionService umsRolePermissionService;

    @Override
    public List<UmsPermission> getPermissionByUserId(String userId,String type) {
        return baseMapper.getAllPermissionByUserId(userId, type);
    }

    @Override
    public List<UmsPermission> getPermissionByRoleId(String roleId, String type) {
        return baseMapper.getAllPermissionByRoleId(roleId,type);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public boolean setPermissionOfRole(String roleId, List<String> permissionIds) {
        umsRolePermissionService.deleteByRoleIdPermissionId(roleId,null);
        List<UmsRolePermission> relation = permissionIds.stream().map(e -> new UmsRolePermission(roleId, e)).collect(Collectors.toList());
        return umsRolePermissionService.saveBatch(relation);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public boolean deleteById(String id) {
        if(umsRolePermissionService.deleteByRoleIdPermissionId(null,id))
            return  this.removeById(id);
        else
            return false;
    }
}
