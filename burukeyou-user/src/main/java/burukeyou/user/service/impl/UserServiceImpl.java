package burukeyou.user.service.impl;

import burukeyou.user.entity.pojo.UmsUser;
import burukeyou.user.mapper.UmsUserMapper;
import burukeyou.user.service.UserService;
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
