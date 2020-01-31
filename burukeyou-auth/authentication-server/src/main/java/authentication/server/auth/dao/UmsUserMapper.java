package authentication.server.auth.dao;

import authentication.server.auth.entity.pojo.UmsUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UmsUserMapper extends BaseMapper<UmsUser> {
}
