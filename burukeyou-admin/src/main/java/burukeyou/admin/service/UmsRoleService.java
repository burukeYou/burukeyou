package burukeyou.admin.service;


import java.util.List;

public interface UmsRoleService {
    /**
     *
     * @param userId 用户id
     * @return 角色列表
     */
    List<String> getByUserId(String userId);
}
