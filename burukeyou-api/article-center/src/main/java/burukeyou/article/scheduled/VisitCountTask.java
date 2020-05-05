package burukeyou.article.scheduled;

import burukeyou.article.entity.bo.VisitCount;
import burukeyou.article.service.ArticleService;
import burukeyou.article.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Service
public class VisitCountTask {

    private final ArticleService articleService;

    private final RedisService redisService;

    public VisitCountTask(ArticleService articleService, RedisService redisService) {
        this.articleService = articleService;
        this.redisService = redisService;
    }

    // [秒] [分] [小时] [日] [月] [周] [年]
    @Scheduled(cron = "30 * * * * ?" )
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void syncVisitCountToDB(){
        log.info("同步redis数据到数据库");
        List<VisitCount> list = redisService.getAllFoucusCountData();
        articleService.updateVisitCountBatch(list);
    }

}
