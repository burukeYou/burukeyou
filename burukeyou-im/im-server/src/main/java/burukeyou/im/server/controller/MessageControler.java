package burukeyou.im.server.controller;

import burukeyou.im.server.IMConnectServer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageControler {

    @Autowired
    private IMConnectServer imConnectServer;

    // 下推消息
    @PostMapping("/sendMsg")
    public String sendMessage(String receivedId,String message){
        try {
            imConnectServer.sendMsg(receivedId,message);
            return "推送成功";
        } catch (JsonProcessingException e) {
           return "推送失败";
        }
    }






}
