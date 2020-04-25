package burukeyou.article.mapper;

import burukeyou.article.entity.dto.ArticleQueryConditionDto;
import burukeyou.article.entity.pojo.AmsArticle;
import burukeyou.article.entity.vo.ArticleDetailVo;
import burukeyou.article.entity.vo.ArticleListlVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArticleMapper extends BaseMapper<AmsArticle> {

    ArticleDetailVo getOneDetailById(@Param("id") String id);


    Page<AmsArticle> getPageCondition(Page<AmsArticle> page, ArticleQueryConditionDto dto);
}
