package burukeyou.common.rabbitmq.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.amqp.core.ExchangeTypes;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExchangeEnum {
    /**
     *    处理访问量，点赞量，评论量的主题交换机
     */
    AMOUNT_DIRECT("burukeyou.amount.direct", ExchangeTypes.DIRECT,true);

    /**
     *    交换机名称
     */
    private String name;

    /**
     * 交换机类型:
     *      direct
     *      topic
     *      fanout
     *      headers
     */
    private String type;

    /**
     *  是否持久化:
     *      true
     *      false
     */
    private boolean durable ;
}
