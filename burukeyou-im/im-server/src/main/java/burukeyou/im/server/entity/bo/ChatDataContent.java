package burukeyou.im.server.entity.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ChatDataContent implements Serializable {

    private static final long serialVersionUID = 6411163408314414183L;


    private Integer action;    //动作类型
    private ChatMessage chatMessage;  // 用户的聊天内容
    private String extend;  // 扩展字段


    public ChatDataContent setAction(Integer action) {
        this.action = action;
        return this;
    }

    public ChatDataContent setChatMessage(ChatMessage chatMessage) {
        this.chatMessage = chatMessage;
        return this;
    }

    public ChatDataContent setExtend(String extend) {
        this.extend = extend;
        return this;
    }
}
