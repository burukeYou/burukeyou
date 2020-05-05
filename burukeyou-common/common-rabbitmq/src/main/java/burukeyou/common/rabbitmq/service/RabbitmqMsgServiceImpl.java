package burukeyou.common.rabbitmq.service;

import burukeyou.common.rabbitmq.entity.pojo.RabbitmqMsg;
import burukeyou.common.rabbitmq.mapper.RabbitmqMsgMapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class RabbitmqMsgServiceImpl extends ServiceImpl<RabbitmqMsgMapper, RabbitmqMsg> implements RabbitmqMsgService{

    @Override
    public boolean updateMsgStatus(String msgId, Integer status) {
        return super.update(new UpdateWrapper<RabbitmqMsg>().lambda().set(RabbitmqMsg::getStatus,status).eq(RabbitmqMsg::getId,msgId));
    }
}
