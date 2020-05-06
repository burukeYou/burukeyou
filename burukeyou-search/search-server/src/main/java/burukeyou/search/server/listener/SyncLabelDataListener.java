package burukeyou.search.server.listener;

import burukeyou.search.server.temp.SysLabel;
import burukeyou.search.server.temp.SysLableServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SyncLabelDataListener implements ApplicationListener<ApplicationStartedEvent> {

    @Autowired
    private SysLableServiceImpl sysLableService;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {

        List<SysLabel> list = sysLableService.list();
        for (SysLabel e : list) {
            System.out.println(e);
        }


    }
}
