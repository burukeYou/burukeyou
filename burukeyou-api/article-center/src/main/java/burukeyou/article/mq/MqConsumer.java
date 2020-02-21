package burukeyou.article.mq;


import burukeyou.article.entity.bo.CountIncrementMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class MqConsumer {

    /*@RabbitListener(bindings = @QueueBinding(  //建立绑定
            value = @Queue(value = "countIncreQueue", durable="true"), //指定队列名称,设置是否持久化
            exchange = @Exchange(value = "articleExchange", //交换机名称
                    durable="true",
                    type= "topic",  //交换机类型
                    ignoreDeclarationExceptions = "true"), //支持过滤
            key = "countIncre.*"   //指定路由key: 通配符-什么格式的key才会接收到
    )
    )
    @RabbitHandler
    public void onMessage(Map res) throws Exception {
        System.out.println("------------------接收到消息（string）-------------------");
        System.out.println(res);

        System.out.println("-------------------------------------\n\n");

    }*/

    @RabbitListener(bindings = @QueueBinding(  //建立绑定
            value = @Queue(value = "countIncreQueue", durable="true"), //指定队列名称,设置是否持久化
            exchange = @Exchange(value = "articleExchange", //交换机名称
                    durable="true",
                    type= "topic",  //交换机类型
                    ignoreDeclarationExceptions = "true"), //支持过滤
            key = "countIncre.*"   //指定路由key: 通配符-什么格式的key才会接收到
    )
    )
    @RabbitHandler
    public void onMessage(CountIncrementMsg res, @Headers Map<String, Object> headers) throws Exception {
        System.out.println("------------------接收到消息（string）-------------------");
        System.out.println(res);
        Long deliveryTag = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);//AmqpHeaders.DELIVERY_TAG消息的id标签属性

        System.out.println("-------------------------------------\n\n");

    }



}
