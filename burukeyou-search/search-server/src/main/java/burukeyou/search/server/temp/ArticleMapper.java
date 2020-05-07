package burukeyou.search.server.temp;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface ArticleMapper extends BaseMapper<AmsArticle> {

    @Select("select * from ams_article")
    List<AmsArticle> all();
}
