package burukeyou.admin.service.impl;

import burukeyou.admin.entity.pojo.UmsRole;
import burukeyou.admin.mapper.UmsRoleMapper;
import burukeyou.admin.service.UmsRolePermissionService;
import burukeyou.admin.service.UmsAdminRoleService;
import burukeyou.admin.service.UmsRoleService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UmsRoleServiceImpl extends ServiceImpl<UmsRoleMapper, UmsRole> implements UmsRoleService {

    @Autowired
    private UmsAdminRoleService adminRoleRelationService;

    @Autowired
    private UmsRolePermissionService umsRolePermissionService;


    @Override
    public List<UmsRole> getByUserId(String userId) {
        List<UmsRole> roles = baseMapper.findByUserId(userId);
        return roles;
    }

    @Override
    public boolean addOrUpdate(UmsRole umsRole) {
        return this.saveOrUpdate(umsRole);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public boolean deleteRoleById(String id) {
        adminRoleRelationService.deleteByUserIdRoleId(null,id);
        umsRolePermissionService.deleteByRoleIdPermissionId(id,null);
        return  this.removeById(id);
    }

    @Override
    public Page<UmsRole> getPage(int currentPage, int size) {
        Page<UmsRole> page = new Page<>(currentPage,size);
        return this.page(page);
    }
}
