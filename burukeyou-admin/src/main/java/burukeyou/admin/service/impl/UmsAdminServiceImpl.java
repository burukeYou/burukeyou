package burukeyou.admin.service.impl;

import burukeyou.admin.entity.pojo.UmsUser;
import burukeyou.admin.mapper.UmsAdminMapper;
import burukeyou.admin.service.UmsAdminService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UmsAdminServiceImpl extends ServiceImpl<UmsAdminMapper,UmsUser> implements UmsAdminService {

    @Override
    public UmsUser getByUniqueId(String uniqueId) {
        UmsUser one = this.getOne(new QueryWrapper<UmsUser>()
                .eq("username", uniqueId).or()
                .eq("mobile", uniqueId));
        return one;
    }
}
