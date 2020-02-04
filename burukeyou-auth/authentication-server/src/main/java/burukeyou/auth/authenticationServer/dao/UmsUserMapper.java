package burukeyou.auth.authenticationServer.dao;

import burukeyou.auth.authenticationServer.entity.pojo.UmsUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UmsUserMapper extends BaseMapper<UmsUser> {
}
