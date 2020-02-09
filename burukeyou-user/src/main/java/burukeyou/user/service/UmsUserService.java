package burukeyou.user.service;

import burukeyou.user.entity.pojo.UmsUsers;

public interface UmsUserService {
    /**
     *      save or update user info
     * @param umsUsers
     * @return success or fail
     */
    Boolean saveOrupdate(UmsUsers umsUsers);

    /**
     *
     * @param uniqueId 手机号或者电话或者邮箱
     * @return
     */
    UmsUsers getByUniqueId(String uniqueId);
}
