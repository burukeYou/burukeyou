package burukeyou.system.mq;

import burukeyou.common.rabbitmq.entity.bo.ArticleLabel;
import burukeyou.common.rabbitmq.entity.constant.ExchangeConstant;
import burukeyou.common.rabbitmq.entity.constant.QueueConstant;
import burukeyou.common.rabbitmq.entity.enums.RabbitmqMsgStatusEnum;
import burukeyou.common.rabbitmq.entity.pojo.RabbitmqMsg;
import burukeyou.common.rabbitmq.service.RabbitmqMsgService;
import burukeyou.system.entity.pojo.SysLabelArticle;
import burukeyou.system.service.SysLabelArticleService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Component
public class Consumer {

    @Autowired
    private RabbitmqMsgService rabbitmqMsgService;

    @Autowired
    private SysLabelArticleService labelArticleService;

    @RabbitHandler
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = QueueConstant.ARTICLE_LABEL,durable =  "true"),
            exchange = @Exchange(name = ExchangeConstant.ARTICLE_DIRECT,type = ExchangeTypes.DIRECT),
            key = "artile.label"
    ))
    public void consumeArticleLabelRation(@Payload ArticleLabel articleLabel, Channel channel, @Headers Map<String, Object> headers) throws IOException {
        log.info("增加文章{}标签",articleLabel.getArticleId());
        Long ic = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
        String msgId = (String)headers.get("spring_returned_message_correlation");

        // 确认消息是否到达消费者
        rabbitmqMsgService.updateMsgStatus(String.valueOf(msgId),RabbitmqMsgStatusEnum.CONSUME_SUCCESS.getStatus());
        channel.basicAck(ic,false);

        // 开始消费
        RabbitmqMsg msg = rabbitmqMsgService.getById(msgId);//spring_returned_message_correlation -> 1257270954080976896
        if (msg != null && msg.getStatus() == RabbitmqMsgStatusEnum.CONSUME_SUCCESS.getStatus()){
            log.error("重复消费消息{}",msgId);
        }else {
            try {
                articleLabel.getLabelIds().forEach(e -> labelArticleService.save(new SysLabelArticle(articleLabel.getArticleId(),e)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
