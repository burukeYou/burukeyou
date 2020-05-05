package burukeyou.common.rabbitmq.entity.enums;



public enum  QueueEnum {
    /**
     *    访问量队列
     */
    VISIT_COUNT("burukeyou.amount.visit",ExchangeEnum.AMOUNT_DIRECT,"amount.visit.*","true"),

    /**
     *   点赞量队列
     */
    THUMBUP_COUNT("burukeyou.amount.thumbup",ExchangeEnum.AMOUNT_DIRECT,"amount.thumbup.*","true"),

    /**
     *   评论量队列
     */
    COMMENT_COUNT("burukeyou.amount.comment",ExchangeEnum.AMOUNT_DIRECT,"amount.comment.*","true");


    /**
     * 队列名称
     */
    public  String name ;

    /**
     *  订阅的交换机
     */
    private  ExchangeEnum exchange;

    /**
     *  订阅的路由键
     */
    private  String routeKey;

    /**
     *  是否持久化:
     *      true
     *      false
     */
    private  String durable;


    QueueEnum( String name, ExchangeEnum exchange, String routeKey, String durable) {
        this.name = name;
        this.exchange = exchange;
        this.routeKey = routeKey;
        this.durable = durable;
    }

    public  String getName() {
        return name;
    }

    public  ExchangeEnum getExchange() {
        return exchange;
    }

    public  String getRouteKey() {
        return routeKey;
    }

    public  String getDurable() {
        return durable;
    }
}
