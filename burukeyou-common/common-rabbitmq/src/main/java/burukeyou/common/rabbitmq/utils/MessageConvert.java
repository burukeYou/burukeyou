package burukeyou.common.rabbitmq.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;

import java.io.IOException;

public class MessageConvert {

    private final static ObjectMapper jsonUtil = new ObjectMapper();

    private MessageConvert(){}

    public static Message objToMsg(Object obj) throws JsonProcessingException {
        if (null == obj)
            return null;

        Message message = MessageBuilder.withBody(jsonUtil.writeValueAsString(obj).getBytes()).build();
        message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        message.getMessageProperties().setContentType(MessageProperties.CONTENT_TYPE_JSON);
        return message;
    }

    public static <T> T msgToObj(Message message, Class<T> clazz) throws IOException {
        if (null == message || null == clazz)
            return null;
        T obj = jsonUtil.readValue(new String(message.getBody()),clazz);
        return obj;
    }


}
