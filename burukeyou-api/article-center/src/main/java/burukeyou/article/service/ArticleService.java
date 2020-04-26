package burukeyou.article.service;

import burukeyou.article.entity.dto.ArticleDto;
import burukeyou.article.entity.dto.ArticleQueryConditionDto;
import burukeyou.article.entity.pojo.AmsArticle;
import burukeyou.article.entity.vo.ArticleDetailVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;


public interface ArticleService extends IService<AmsArticle> {

    /**
     *  发表文章
     * @return
     */
    boolean publishArticle( ArticleDto articleDto);

    boolean insertOrUpdate(AmsArticle amsArticle);

    boolean deleteById(String id);

    /**
     *   not current login user only can  get the article  of public and state is safe
     * @param id
     * @return
     */
    AmsArticle getById(String id);


    /**
     *     多条件分页获取
     * @param conditionDto
     * @return
     */
    Page<AmsArticle> getListByUserId(ArticleQueryConditionDto conditionDto);

}
