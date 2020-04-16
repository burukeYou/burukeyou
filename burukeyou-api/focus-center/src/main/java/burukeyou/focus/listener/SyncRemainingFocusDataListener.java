package burukeyou.focus.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

@Component
public class SyncRemainingFocusDataListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        // todo 当服务重启时把剩余缓存在redis的focus 数据直接同步到数据库
    }
}
