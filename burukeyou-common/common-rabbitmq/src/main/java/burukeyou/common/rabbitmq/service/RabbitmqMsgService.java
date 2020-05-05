package burukeyou.common.rabbitmq.service;

import burukeyou.common.rabbitmq.entity.pojo.RabbitmqMsg;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDateTime;
import java.util.List;


public interface RabbitmqMsgService extends IService<RabbitmqMsg> {

    /**
     *      更新消息状态
     * @param msgId
     * @param status
     * @return
     */
    boolean updateMsgStatus(String msgId,Integer status);

    /**
     *      获得所有投递失败的消息
     *              比如消息状态为发送中的，或者是消息超时的
     * @return
     */
    List<RabbitmqMsg> getAllFailSendMsg();

    /**
     *      更新消息重试次数和投递时间
     * @param id
     * @param plusMinutes
     */
    void incrMsgRetryCountAndNextRetryTime(String id, LocalDateTime plusMinutes);
}
