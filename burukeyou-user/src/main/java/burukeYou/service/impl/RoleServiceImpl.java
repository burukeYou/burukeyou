package burukeYou.service.impl;

import burukeYou.entity.pojo.UmsRole;
import burukeYou.mapper.UmsRoleMapper;
import burukeYou.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<UmsRoleMapper, UmsRole> implements RoleService {


    private final UmsRoleMapper umsRoleMapper;

    public RoleServiceImpl(UmsRoleMapper umsRoleMapper) {
        this.umsRoleMapper = umsRoleMapper;
    }

    @Override
    public List<String> getByUserId(String userId) {
        List<String> roles = umsRoleMapper.findByUserId(userId);

        return roles;
    }
}
