package burukeyou.article.rpc.fallbackFactory;

import burukeyou.article.rpc.LikeServiceRPC;
import burukeyou.common.core.entity.vo.ResultVo;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
