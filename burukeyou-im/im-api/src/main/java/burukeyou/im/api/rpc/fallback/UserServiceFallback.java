package burukeyou.im.api.rpc.fallback;


import burukeyou.common.core.entity.vo.ResultVo;
import burukeyou.im.api.rpc.UserServiceRPC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserServiceFallback implements UserServiceRPC {
    @Value("${spring.application.name}")
    private String server;

    @Override
    public ResultVo getUserByUniqueId(String uniqueId) {
        log.error("{}-调用用户服务降级"+server);
        return null;
    }
}
