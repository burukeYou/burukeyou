package burukeyou.common.rabbitmq.config;

import burukeyou.common.rabbitmq.mapper.RabbitmqMsgMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@ComponentScan("burukeyou.common.rabbitmq")
@MapperScan("burukeyou.common.rabbitmq.mapper")
public class RabbitMqConfig {

    @Autowired
    public ConnectionFactory connectionFactory;

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());  //发送的消息自动序列化为json

        return template;
    }



}