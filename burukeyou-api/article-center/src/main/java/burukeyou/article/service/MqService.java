package burukeyou.article.service;

import java.util.List;

public interface MqService {

    /**
     *      增加文章访问量
     * @param entityId
     */
    void incrVisitCount(String entityId);

    /**
     *      建立标签和文章的关系
     * @param articleId
     * @param labelIds
     */
    void buildArticleWithLabelRelations(String articleId, List<String> labelIds);
}
