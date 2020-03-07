package burukeyou.admin.service.impl;

import burukeyou.admin.entity.pojo.UmsAdminRole;
import burukeyou.admin.mapper.UmsAdminRoleMapper;
import burukeyou.admin.service.UmsAdminRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UmsAdminRoleServiceImpl extends ServiceImpl<UmsAdminRoleMapper, UmsAdminRole> implements UmsAdminRoleService {

    @Override
    public int deleteByUserIdRoleId(String userId, String roleId) {
        return baseMapper.deleteByUserIdRoleId(userId,roleId);
    }
}
