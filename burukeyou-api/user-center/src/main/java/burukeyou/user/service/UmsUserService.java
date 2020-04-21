package burukeyou.user.service;

import burukeyou.user.entity.pojo.UmsUser;

public interface UmsUserService {
    /**
     *      save or update user info
     * @param umsUser
     * @return success or fail
     */
    Boolean saveOrupdate(UmsUser umsUser);

    /**
     *
     * @param uniqueId 手机号或者电话或者邮箱
     * @return
     */
    UmsUser getByUniqueId(String uniqueId);
}
