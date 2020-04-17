package burukeyou.admin.mapper;

import burukeyou.admin.entity.pojo.UmsAdminRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface UmsAdminRoleMapper extends BaseMapper<UmsAdminRole> {


    int deleteByUserIdRoleId(@Param("userId") String userId, @Param("roleId") String roleId);
}
