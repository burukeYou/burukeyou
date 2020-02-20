package burukeyou.im.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConnectionServer {

    @GetMapping
    public String getServerInstance(){
        // 从服务注册中心获取一台实例返回
        return null;
    }

}
