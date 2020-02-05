package burukeyou.admin.service.impl;

import burukeyou.admin.entity.pojo.UmsRole;
import burukeyou.admin.mapper.UmsRoleMapper;
import burukeyou.admin.service.UmsRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UmsRoleServiceImpl extends ServiceImpl<UmsRoleMapper, UmsRole> implements UmsRoleService {


    private final UmsRoleMapper umsRoleMapper;

    public UmsRoleServiceImpl(UmsRoleMapper umsRoleMapper) {
        this.umsRoleMapper = umsRoleMapper;
    }

    @Override
    public List<String> getByUserId(String userId) {
        List<String> roles = umsRoleMapper.findByUserId(userId);

        return roles;
    }
}
