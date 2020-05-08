package burukeyou.im.server.listener;

import burukeyou.im.server.IMConnectServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ImStarterListener implements ApplicationListener<ApplicationStartedEvent> {

    private final IMConnectServer imConnectServer;

    public ImStarterListener(IMConnectServer imConnectServer) {
        this.imConnectServer = imConnectServer;
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent cre) {
         try {
             System.out.println("尝试启动IM服务器.....");
             imConnectServer.startServer();
         } catch (Exception e) {
             e.printStackTrace();
             log.error("启动IM服务器异常.....");
         }

    }
}
