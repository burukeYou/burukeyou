package burukeyou.user.mapper;

import burukeyou.user.entity.pojo.UmsRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UmsRoleMapper extends BaseMapper<UmsRole> {


    @Select("select code from ums_roles where id in (select role_id from ums_admin_role_relation where user_id = #{userId})")
    List<String> findByUserId(@Param("userId") String userId);

}
