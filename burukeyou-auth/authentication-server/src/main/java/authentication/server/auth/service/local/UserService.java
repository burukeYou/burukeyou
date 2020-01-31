package authentication.server.auth.service.local;

import authentication.server.auth.entity.vo.UserTokenVo;

public interface UserService {
    /**
     *  根据账号或者用户id或者手机号获取用户
     * @param uniqueId
     * @return UmsUser
     */
    UserTokenVo getUserByUserUniqueId(String uniqueId);
}
