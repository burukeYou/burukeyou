package burukeyou.like.service.impl;

import burukeyou.common.rabbitmq.entity.constant.ExchangeConstant;
import burukeyou.common.rabbitmq.utils.MessageConvert;
import burukeyou.like.entity.bo.LikeMsg;
import burukeyou.like.service.MqService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqServiceImpl implements MqService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void postLike(LikeMsg like) {
        Message message = MessageConvert.objToMsg(like);
        rabbitTemplate.convertAndSend(ExchangeConstant.LIKE,"like.postlike",message);
    }

}
