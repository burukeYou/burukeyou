package burukeyou.article.mq;

import burukeyou.article.entity.bo.CountIncrementMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class MqSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void send(String entityId,String type){
        log.info("开始发送消息:{},{}",entityId,type);

        Map<String, Object> msg = new HashMap<>();
        msg.put("entityId", entityId);
        msg.put("type", type);

        CorrelationData correlationData = new CorrelationData("1234567890");
        rabbitTemplate.convertAndSend("articleExchange", "countIncre.visit", msg, correlationData);
    }

    public void send(CountIncrementMsg countIncrementMsg){
        log.info("开始发送消息:{}",countIncrementMsg);

        CorrelationData correlationData = new CorrelationData("1234567890");
        rabbitTemplate.convertAndSend("articleExchange", "countIncre.visit", countIncrementMsg, correlationData);
    }














}
