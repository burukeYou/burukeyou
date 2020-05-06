package burukeyou.search.server.temp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface SysLabelMapper extends BaseMapper<SysLabel> {

    @Update("update sys_label set focus_count = focus_count + #{count} where id = #{id}")
    int updateFoucusCount(@Param("id") String id, @Param("count") int count);
}
