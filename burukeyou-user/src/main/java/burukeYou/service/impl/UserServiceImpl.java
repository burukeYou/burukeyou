package burukeYou.service.impl;

import burukeYou.entity.pojo.UmsUser;
import burukeYou.mapper.UmsUserMapper;
import burukeYou.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UmsUserMapper,UmsUser> implements UserService {

    @Override
    public UmsUser getByUniqueId(String uniqueId) {
        UmsUser one = this.getOne(new QueryWrapper<UmsUser>()
                .eq("username", uniqueId).or()
                .eq("mobile", uniqueId));
        return one;
    }
}
