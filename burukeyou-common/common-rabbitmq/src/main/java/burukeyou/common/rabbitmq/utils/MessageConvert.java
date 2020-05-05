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

    public static Message objToMsg(Object obj)  {
        if (null == obj)
            return null;

        Message message = null;
        try {
            message = MessageBuilder.withBody(jsonUtil.writeValueAsString(obj).getBytes()).build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        message.getMessageProperties().setContentType(MessageProperties.CONTENT_TYPE_JSON);
        return message;
    }

    public static <T> T msgToObj(Message message, Class<T> clazz)  {
        if (null == message || null == clazz)
            return null;
        T obj = null;
        try {
            obj = jsonUtil.readValue(new String(message.getBody()),clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }


}
