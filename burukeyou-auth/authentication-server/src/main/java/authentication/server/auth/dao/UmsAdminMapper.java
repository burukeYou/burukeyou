package authentication.server.auth.dao;

import authentication.server.auth.entity.vo.UserTokenVo;
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
