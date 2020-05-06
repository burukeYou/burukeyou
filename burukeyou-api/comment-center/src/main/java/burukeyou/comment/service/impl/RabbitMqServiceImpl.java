package burukeyou.comment.service.impl;

import burukeyou.comment.service.MqService;
import burukeyou.common.rabbitmq.entity.bo.NotificationDto;
import burukeyou.common.rabbitmq.entity.constant.ExchangeConstant;
import burukeyou.common.rabbitmq.utils.MessageConvert;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqServiceImpl implements MqService {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMqServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publishNotification(NotificationDto notificationDto) {
        Message message = MessageConvert.objToMsg(notificationDto);
        rabbitTemplate.convertAndSend(ExchangeConstant.NOTIFICATION,"notification.publish",message);
    }
}
