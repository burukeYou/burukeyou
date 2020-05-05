package burukeyou.article.mq;

import burukeyou.article.service.RedisService;
import burukeyou.common.rabbitmq.entity.constant.ExchangeConstant;
import burukeyou.common.rabbitmq.entity.constant.QueueConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ArticleVisitCountConsumer {

    private final RedisService redisService;

    public ArticleVisitCountConsumer(RedisService redisService) {
        this.redisService = redisService;
    }

    @RabbitHandler
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = QueueConstant.AMOUNT_VISIT_ARTICLE,durable =  "true"),
            exchange = @Exchange(name = ExchangeConstant.AMOUNT_DIRECT),
            key = "amount.visit.article"
    ))
    public void onMessage(String articleId){
        log.info("增加文章{}访问量",articleId);
        redisService.incrArticleVisitCount(articleId);
    }

}
