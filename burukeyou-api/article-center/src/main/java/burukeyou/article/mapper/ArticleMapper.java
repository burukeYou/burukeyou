package burukeyou.article.mapper;

import burukeyou.article.entity.pojo.AmsArticle;
import burukeyou.article.entity.vo.ArticleDetailVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArticleMapper extends BaseMapper<AmsArticle> {



     ArticleDetailVo getOneDetailById(@Param("id") String id);


}
