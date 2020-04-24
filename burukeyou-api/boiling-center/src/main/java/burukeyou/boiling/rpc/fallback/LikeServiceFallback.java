package burukeyou.boiling.rpc.fallback;

import burukeyou.boiling.rpc.LikeServiceRPC;
import burukeyou.common.core.entity.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class LikeServiceFallback implements LikeServiceRPC {


    @Override
    public ResultVo<Map<String, Boolean>> judgeIsLikeList(String parentType, List<String> parentIdList) {
        log.error("调用点赞服务熔断降级");
        return null;
    }
}
