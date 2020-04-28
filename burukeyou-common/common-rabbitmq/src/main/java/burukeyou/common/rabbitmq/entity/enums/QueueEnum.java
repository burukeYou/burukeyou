package burukeyou.common.rabbitmq.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum  QueueEnum {
    /**
     *    访问量队列
     */
    VISIT_COUNT("burukeyou.amount.visit",ExchangeEnum.AMOUNT_TOPIC,"amount.visit.*"),

    /**
     *   点赞量队列
     */
    THUMBUP_COUNT("burukeyou.amount.thumbup",ExchangeEnum.AMOUNT_TOPIC,"amount.thumbup.*"),

    /**
     *   评论量队列
     */
    COMMENT_COUNT("burukeyou.amount.comment",ExchangeEnum.AMOUNT_TOPIC,"amount.comment.*");


    /**
     * 队列名称
     */
    private String name;

    /**
     *  订阅的交换机
     */
    private ExchangeEnum exchange;

    /**
     *  订阅的路由键
     */
    private String routeKey;

    /**
     *  是否持久化:
     *      true
     *      false
     */
    private String durable = "true";

    QueueEnum(String name, ExchangeEnum exchange, String routeKey) {
        this.name = name;
        this.exchange = exchange;
        this.routeKey = routeKey;
    }
}
