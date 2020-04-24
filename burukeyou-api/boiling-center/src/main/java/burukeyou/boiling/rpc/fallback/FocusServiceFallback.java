package burukeyou.boiling.rpc.fallback;

import burukeyou.boiling.rpc.FocusServiceRPC;
import burukeyou.common.core.entity.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class FocusServiceFallBack implements FocusServiceRPC {

    public ResultVo<Map<String,Boolean>> judgeIsFollwerList(@PathVariable("targetType") String targetType, List<String> targetidList){
            log.error("调用判断是否关注服务熔断降级");
        return null;
    }

}
