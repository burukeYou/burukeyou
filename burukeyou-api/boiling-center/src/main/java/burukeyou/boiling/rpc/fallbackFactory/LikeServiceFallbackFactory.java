package burukeyou.boiling.rpc.fallbackFactory;

import burukeyou.boiling.rpc.LikeServiceRPC;
import burukeyou.boiling.rpc.fallback.LikeServiceFallback;
import burukeyou.common.core.entity.bo.MicroServiceName;
import burukeyou.common.core.entity.vo.ResultVo;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class LikeServiceFallbackFactory implements FallbackFactory<LikeServiceRPC> {

    @Override
    public LikeServiceRPC create(Throwable throwable){
        return new LikeServiceRPC(){
            @Override
            public ResultVo<Map<String, Boolean>> judgeIsLikeList(String parentType, List<String> parentIdList) {
                log.error("调用点赞服务异常", throwable);
                return null;
            }

        };
    }
}
