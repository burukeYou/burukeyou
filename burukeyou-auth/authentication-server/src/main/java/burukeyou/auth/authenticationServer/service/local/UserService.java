package burukeyou.auth.authenticationServer.service.local;

import burukeyou.auth.authenticationServer.entity.vo.UserTokenVo;

public interface UserService {
    /**
     *  根据账号或者用户id或者手机号获取用户
     * @param uniqueId
     * @return UmsUser
     */
    UserTokenVo getUserByUserUniqueId(String uniqueId);
}
