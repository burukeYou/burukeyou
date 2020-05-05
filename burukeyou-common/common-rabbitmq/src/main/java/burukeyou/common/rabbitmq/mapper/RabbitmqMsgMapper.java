package burukeyou.common.rabbitmq.mapper;

import burukeyou.common.rabbitmq.entity.pojo.RabbitmqMsg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface RabbitmqMsgMapper extends BaseMapper<RabbitmqMsg> {



}
