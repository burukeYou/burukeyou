package authentication.server.auth.service.local.impl;

import authentication.server.auth.dao.UmsAdminMapper;
import authentication.server.auth.entity.vo.UserTokenVo;
import authentication.server.auth.service.local.UserService;
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
