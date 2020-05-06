package burukeyou.article.scheduled;

import burukeyou.article.service.MqService;
import burukeyou.common.rabbitmq.entity.bo.ArticleLabel;
import burukeyou.common.rabbitmq.entity.enums.RabbitmqMsgStatusEnum;
import burukeyou.common.rabbitmq.entity.pojo.RabbitmqMsg;
import burukeyou.common.rabbitmq.service.RabbitmqMsgService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 *     todo 后期改成分布式任务
 */
@Slf4j
@Service
public class MsgReliableTask {

    private final RabbitmqMsgService rabbitmqMsgService;

    private final MqService mqService;

    private final ObjectMapper objectMapper;

    public MsgReliableTask(RabbitmqMsgService rabbitmqMsgService, MqService mqService, ObjectMapper objectMapper) {
        this.rabbitmqMsgService = rabbitmqMsgService;
        this.mqService = mqService;
        this.objectMapper = objectMapper;
    }

    /**
     *  对增加文章和标签关系的消息补偿
     */
    // [秒] [分] [小时] [日] [月] [周] [年]
   /* @Scheduled(cron = "30 * * * * ?" )
    public void syncVisitCountToDB() throws IOException {
        List<RabbitmqMsg> msgList = rabbitmqMsgService.getAllFailSendMsg();
        log.info("对失败的{}条消息补偿",msgList.size());

        for (RabbitmqMsg e : msgList) {
            if (e.getRetryCount() >= 3){
                rabbitmqMsgService.updateMsgStatus(e.getId(), RabbitmqMsgStatusEnum.SEND_FAILURE.getStatus());
                log.error("消息{}超过最大重试次数，投递失败",e.getId());
            }else {
                rabbitmqMsgService.incrMsgRetryCountAndNextRetryTime(e.getId(), LocalDateTime.now().plusMinutes(1L));
                ArticleLabel articleLabel = objectMapper.readValue(e.getContent(), ArticleLabel.class);
                mqService.buildArticleWithLabelRelations(articleLabel);
                log.info("消息({})第{}次重新投递消息",e.getId(),e.getRetryCount()+1);
            }
        }
    }*/

}

