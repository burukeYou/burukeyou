package burukeyou.im.server.listener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

/*
@Component
public class NacosServer implements ApplicationListener<ApplicationStartedEvent> {

    @Value("${custom.im.port}")
    private int port;


    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        try {

            String addr = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
*/
