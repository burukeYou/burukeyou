package burukeyou.notification.mq;

import burukeyou.common.rabbitmq.entity.bo.NotificationDto;
import burukeyou.common.rabbitmq.entity.constant.ExchangeConstant;
import burukeyou.common.rabbitmq.entity.constant.QueueConstant;
import burukeyou.common.rabbitmq.utils.MessageConvert;
import burukeyou.notification.service.NotificationService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class Consumer {

    private final NotificationService notificationService;

    public Consumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RabbitHandler
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = QueueConstant.NOTIFICATION_PUBLISH,durable =  "true"),
            exchange = @Exchange(name = ExchangeConstant.NOTIFICATION,type = ExchangeTypes.DIRECT),
            key = "notification.publish"
    ))
    public void onPushNotificationMsg(Message message, Channel channel) throws IOException {
        NotificationDto notification = MessageConvert.msgToObj(message, NotificationDto.class);
        notificationService.publishNotification(notification.getAcceptId(),notification.getContent(),notification.getType());

        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        channel.basicAck(deliveryTag,false);
    }
}
