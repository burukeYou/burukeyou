package burukeyou.like.mq;

import burukeyou.common.rabbitmq.entity.constant.ExchangeConstant;
import burukeyou.common.rabbitmq.entity.constant.QueueConstant;
import burukeyou.common.rabbitmq.utils.MessageConvert;
import burukeyou.like.entity.bo.LikeMsg;
import burukeyou.like.entity.pojo.AmsLike;
import burukeyou.like.service.RedisLikeService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 *      消峰
 */
@Slf4j
@Component
public class LikeConsumer {

    private final RedisLikeService redisLikeService;

    public LikeConsumer(RedisLikeService redisLikeService) {
        this.redisLikeService = redisLikeService;
    }

    // todo 改成配置文件配置
    @RabbitHandler
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = QueueConstant.LIKE,durable =  "true"),
            exchange = @Exchange(name = ExchangeConstant.LIKE),
            key = "like.postlike"
    ))
    public void onLike(Message message, Channel channel) throws IOException {
        LikeMsg amsLike = MessageConvert.msgToObj(message, LikeMsg.class);
        log.info("处理点赞消息:{}",amsLike.toString());

        if (amsLike.isLike()){
            redisLikeService.postLike(amsLike.getUserId(),amsLike.getParentId(),amsLike.getParentType());
        }else {
            redisLikeService.cancelLike(amsLike.getUserId(),amsLike.getParentId(),amsLike.getParentType());
        }

        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        channel.basicAck(deliveryTag,false); // multiple: true是channel级别， false是消费者级别
    }

    // 分队列处理无法保证有序性
   /* @RabbitHandler
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = QueueConstant.UNLIKE,durable =  "true"),
            exchange = @Exchange(name = ExchangeConstant.LIKE),
            key = "like.cancellike"
    ))
    public void onCanelLike(Message message, Channel channel) throws IOException {
       *//* AmsLike amsLike = MessageConvert.msgToObj(message, AmsLike.class);
        log.info("处理取消点赞消息:{}",amsLike.toString());

        redisLikeService.cancelLike(amsLike.getUserId(),amsLike.getParentId(),amsLike.getParentType());

        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        channel.basicAck(deliveryTag,false);*//*
    }
*/


}
