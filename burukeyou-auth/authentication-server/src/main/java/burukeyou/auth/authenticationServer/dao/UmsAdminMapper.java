package burukeyou.auth.authenticationServer.dao;

import burukeyou.auth.authenticationServer.entity.vo.UserTokenVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UmsAdminMapper {

    /**
     * 查找用户
     * @param uniqueId
     * @return UserTokenVo
     */
    UserTokenVo getByUniqueId(String uniqueId);
}
