package burukeyou.article.service;

import burukeyou.article.entity.pojo.AmsArticle;
import burukeyou.article.entity.vo.ArticleDetailVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;


public interface ArticleService extends IService<AmsArticle> {


    boolean insertOrUpdate(AmsArticle amsArticle);

    boolean deleteById(String id);

    /**
     *   not current login user only can  get the article  of public and state is safe
     * @param id
     * @return
     */
    ArticleDetailVo getById(String id);


    Page<AmsArticle> getListByUserId(String userId,long page,long size);

}
