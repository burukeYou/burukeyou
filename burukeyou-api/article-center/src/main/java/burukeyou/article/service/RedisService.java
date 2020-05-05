package burukeyou.article.service;

import burukeyou.article.entity.bo.VisitCount;

import java.util.List;

public interface RedisService {
    /**
     *     增加文章访问量
     * @param articleId 文章id
     */
    void incrArticleVisitCount(String articleId);


    List<VisitCount> getAllFoucusCountData();
}
