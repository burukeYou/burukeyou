package burukeyou.im.server.entity.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatDataContent implements Serializable {

    private static final long serialVersionUID = 6411163408314414183L;

    private Integer action;    //动作类型
    private ChatMessage chatMessage;  // 用户的聊天内容
    private Date time;  // 扩展字段

}
