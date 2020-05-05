package burukeyou.common.rabbitmq.service;

import burukeyou.common.rabbitmq.entity.pojo.RabbitmqMsg;
import com.baomidou.mybatisplus.extension.service.IService;


public interface RabbitmqMsgService extends IService<RabbitmqMsg> {

    /**
     *      更新消息状态
     * @param msgId
     * @param status
     * @return
     */
    boolean updateMsgStatus(String msgId,Integer status);
}
