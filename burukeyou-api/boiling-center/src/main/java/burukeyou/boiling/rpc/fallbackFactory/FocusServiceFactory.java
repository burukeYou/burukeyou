package burukeyou.boiling.rpc.fallbackFactory;

import burukeyou.boiling.rpc.FocusServiceRPC;
import burukeyou.common.core.entity.vo.ResultVo;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class FocusServiceFactory implements FallbackFactory<FocusServiceRPC> {

    @Override
    public FocusServiceRPC create(Throwable throwable) {
        return new FocusServiceRPC(){
            @Override
            public ResultVo<Map<String, Boolean>> judgeIsFollwerList(String targetType, List<String> targetidList) {
                log.error("调用关注服务降级",throwable);
                return null;
            }
        };
    }
}
