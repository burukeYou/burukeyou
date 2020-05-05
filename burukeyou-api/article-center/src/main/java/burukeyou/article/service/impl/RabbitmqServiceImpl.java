package burukeyou.article.service.impl;

import burukeyou.article.service.MqService;
import burukeyou.common.core.utils.IdWorker;
import burukeyou.common.rabbitmq.entity.bo.ArticleLabel;
import burukeyou.common.rabbitmq.entity.constant.ExchangeConstant;
import burukeyou.common.rabbitmq.entity.enums.RabbitmqMsgStatusEnum;
import burukeyou.common.rabbitmq.entity.pojo.RabbitmqMsg;
import burukeyou.common.rabbitmq.service.RabbitmqMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class RabbitmqServiceImpl implements MqService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitmqMsgService rabbitmqMsgService;

    @Override
    public void incrVisitCount(String entityId){
        rabbitTemplate.convertAndSend(ExchangeConstant.AMOUNT_DIRECT,"amount.visit.article",entityId);
    }

    //todo 保证消息百分百投递成功
    @Override
    public void buildArticleWithLabelRelations(String articleId, List<String> labelIds) {
        ArticleLabel content = new ArticleLabel(articleId, labelIds);
        RabbitmqMsg msg = new RabbitmqMsg(new IdWorker().nextId()+"",content,ExchangeConstant.ARTICLE_DIRECT,"artile.label");
        // 插入失败 =》 快速失败
        rabbitmqMsgService.saveOrUpdate(msg);

        //(确认是否到达exchange)
        // 通过实现 ConfirmCallback 接口，消息发送到 Broker 后触发回调，确认消息是否到达 Broker 服务器，也就是只确认是否正确到达 Exchange 中
        rabbitTemplate.setConfirmCallback(confirmCallback);

        rabbitTemplate.setMandatory(true); // 设置路透不到队列后是否触发return回调，否则MQ直接抛弃消息
        rabbitTemplate.setReturnCallback(returnCallback);

        CorrelationData correlationData = new CorrelationData(msg.getId());
        rabbitTemplate.convertAndSend(ExchangeConstant.ARTICLE_DIRECT,"artile.label",content,correlationData);
    }


    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            System.out.println("=============== 是否路由到MQ节点回调=======================\n");
            System.out.println(correlationData);

            System.out.println(ack);
            System.out.println(cause);

            String msgId = correlationData.getId();
            if (ack){
                rabbitmqMsgService.updateMsgStatus(msgId, RabbitmqMsgStatusEnum.SEND_SUCCESS.getStatus());
            }else {
                rabbitmqMsgService.updateMsgStatus(msgId, RabbitmqMsgStatusEnum.SEND_FAILURE.getStatus());
            }
        }
    };


    final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
        @Override
        public void returnedMessage(Message message, int replyCode, String replyText,
                                    String exchange, String routingKey) {
            System.out.println("=============== 是否从交换机路由到队列回调=======================\n");
            System.err.println(message.getBody());
            System.err.println("exchange: " + exchange + ", routingKey: "
                    + routingKey + ", replyCode: " + replyCode + ", replyText: " + replyText);

            System.out.println("=======================================\n\n");

        }
    };


}
