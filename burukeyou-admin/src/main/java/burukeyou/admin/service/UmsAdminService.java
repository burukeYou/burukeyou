package burukeyou.admin.service;


import burukeyou.admin.entity.pojo.UmsUser;

public interface UmsAdminService {

    /** 根据用户账号或者手机号查找用户
     *
     * @param uniqueId
     * @return admin
     */
    UmsUser getByUniqueId(String uniqueId);
}
