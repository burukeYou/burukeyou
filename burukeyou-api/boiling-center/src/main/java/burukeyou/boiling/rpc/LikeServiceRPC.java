package burukeyou.boiling.rpc;

import burukeyou.boiling.rpc.fallback.LikeServiceFallback;
import burukeyou.boiling.rpc.fallbackFactory.LikeServiceFallbackFactory;
import burukeyou.common.core.entity.bo.MicroServiceName;
import burukeyou.common.core.entity.vo.ResultVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = MicroServiceName.LIKE_SERVER,fallbackFactory =  LikeServiceFallbackFactory.class)
public interface LikeServiceRPC {


    @GetMapping("/like/{parentType}")
    ResultVo<Map<String,Boolean>> judgeIsLikeList(@PathVariable("parentType") String parentType,
                                                  @RequestParam("parentIdList") List<String> parentIdList);

}
