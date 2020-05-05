package burukeyou.common.rabbitmq.entity.enums;

public enum  RabbitmqMsgStatusEnum {

    SENDING(0), // 发送中

    SEND_SUCCESS(1), // 发送成功

    SEND_FAILURE(2), // 发送失败

    CONSUME_SUCCESS(3); // 消费成功


    private int status;

    RabbitmqMsgStatusEnum(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
