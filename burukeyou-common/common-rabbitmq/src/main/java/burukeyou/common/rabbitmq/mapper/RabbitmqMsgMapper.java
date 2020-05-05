package burukeyou.common.rabbitmq.mapper;

import burukeyou.common.rabbitmq.entity.pojo.RabbitmqMsg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface RabbitmqMsgMapper extends BaseMapper<RabbitmqMsg> {

    @Select("select * from rabbitmq_msg  where status = 1 and next_try_time <= now()")
    List<RabbitmqMsg> getAllFailSendMsg();

    @Update("update rabbitmq_msg set retry_count = retry_count + 1,next_try_time = #{nextTime}  where id = #{id} for update")
    void updateRetryCountAndNextRetryTime(@Param("id") String id, @Param("nextTime") LocalDateTime nextTime);
}
