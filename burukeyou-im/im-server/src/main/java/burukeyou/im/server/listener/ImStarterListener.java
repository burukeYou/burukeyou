package burukeyou.im.server.listener;

import burukeyou.im.server.IMConnectServer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ImStarterListener implements ApplicationListener<ContextRefreshedEvent> {

    private final IMConnectServer imConnectServer;

    public ImStarterListener(IMConnectServer imConnectServer) {
        this.imConnectServer = imConnectServer;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent cre) {
         if (cre.getApplicationContext().getParent() == null) {
             try {
                 imConnectServer.startServer();
             } catch (Exception e) {
                 e.printStackTrace();
             }
         }
    }
}
