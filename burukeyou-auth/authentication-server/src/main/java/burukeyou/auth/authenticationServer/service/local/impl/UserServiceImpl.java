package burukeyou.auth.authenticationServer.service.local.impl;

import burukeyou.auth.authenticationServer.entity.vo.UserTokenVo;
import burukeyou.auth.authenticationServer.dao.UmsAdminMapper;
import burukeyou.auth.authenticationServer.service.local.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl  implements UserService {


    private final UmsAdminMapper umsUserMapper;

    public UserServiceImpl(UmsAdminMapper umsUserMapper) {
        this.umsUserMapper = umsUserMapper;
    }

    @Override
    public UserTokenVo getUserByUserUniqueId(String uniqueId) {
        return umsUserMapper.getByUniqueId(uniqueId);
    }
}
