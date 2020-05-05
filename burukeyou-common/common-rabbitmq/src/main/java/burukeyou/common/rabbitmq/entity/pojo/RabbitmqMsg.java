package burukeyou.common.rabbitmq.entity.pojo;

import burukeyou.common.rabbitmq.entity.enums.RabbitmqMsgStatusEnum;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("rabbitmq_msg")
public class RabbitmqMsg {

    @TableId(type = IdType.INPUT)
    private String id;

    private String content;

    private String exchange;

    private String routekey  ;

    private int status;

    private int retryCount;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    private LocalDateTime nextTryTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;



    public RabbitmqMsg(String id, Object content, String exchange, String routekey) {
        this.id = id;
        try {
            this.content = new ObjectMapper().writeValueAsString(content);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        this.exchange = exchange;
        this.routekey = routekey;


        this.status = RabbitmqMsgStatusEnum.SENDING.getStatus();
        this.retryCount = 0;
        this.nextTryTime = LocalDateTime.now().plusMinutes(1L);
    }
}
