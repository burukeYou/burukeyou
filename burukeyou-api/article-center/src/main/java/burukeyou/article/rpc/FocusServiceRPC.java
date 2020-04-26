package burukeyou.article.rpc;

import burukeyou.article.rpc.fallbackFactory.FocusServiceFactory;
import burukeyou.common.core.entity.bo.MicroServiceName;
import burukeyou.common.core.entity.vo.ResultVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 *
 */
@FeignClient(name = MicroServiceName.FOCUS_SERVER,fallbackFactory = FocusServiceFactory.class)
public interface FocusServiceRPC {

    @GetMapping("/focus/{targetType}")
    ResultVo<Map<String,Boolean>> judgeIsFollwerList(@PathVariable("targetType") String targetType, @RequestParam("targetidList") List<String> targetidList);


}
