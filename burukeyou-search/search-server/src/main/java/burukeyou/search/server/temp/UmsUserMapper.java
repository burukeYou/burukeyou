package burukeyou.search.server.temp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UmsUserMapper extends BaseMapper<UmsUser> {

    @Select("select * from ums_users")
    List<UmsUser> all();
}
