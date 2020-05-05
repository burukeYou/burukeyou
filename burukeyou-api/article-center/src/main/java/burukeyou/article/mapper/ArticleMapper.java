package burukeyou.article.mapper;

import burukeyou.article.entity.bo.VisitCount;
import burukeyou.article.entity.dto.ArticleQueryConditionDto;
import burukeyou.article.entity.pojo.AmsArticle;
import burukeyou.article.entity.vo.ArticleDetailVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ArticleMapper extends BaseMapper<AmsArticle> {

    ArticleDetailVo getOneDetailById(@Param("id") String id);


    Page<AmsArticle> getPageCondition(Page<AmsArticle> page, ArticleQueryConditionDto dto);

    @Update("update ams_article set visits_count = visits_count + #{e.growthCount}  where id = #{e.entityId}")
    void updateVisitCount(@Param("e") VisitCount e);

    //void updateVisitCountBatch(@Param("list") List<VisitCount> list);
}
