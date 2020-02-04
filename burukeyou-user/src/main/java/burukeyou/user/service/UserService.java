package burukeyou.user.service;


import burukeyou.user.entity.pojo.UmsUser;

public interface UserService {

    /** 根据用户账号或者手机号查找用户
     *
     * @param uniqueId
     * @return user
     */
    UmsUser getByUniqueId(String uniqueId);
}
