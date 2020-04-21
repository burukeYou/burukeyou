package burukeyou.system.rpc;

import burukeyou.common.core.entity.bo.MicroServiceName;
import burukeyou.common.core.entity.vo.ResultVo;
import burukeyou.system.rpc.fallBack.FocusServiceFallBack;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 *
 */
@FeignClient(name = MicroServiceName.FOCUS_SERVER,fallback = FocusServiceFallBack.class)
public interface FocusServiceRPC {

    @GetMapping("/focus/{targetType}")
    ResultVo<Map<String,Boolean>> judgeIsFollwerList(@PathVariable("targetType") String targetType, @RequestParam("targetidList") List<String> targetidList);

    @GetMapping(value = "/focus/{userId}/{targetType}/page")
    ResultVo<Page<String>> getUserFocusTargetPage(@PathVariable("userId") String userId,
                                                  @PathVariable("targetType") String targetType,
                                                  @RequestParam("page") int page, @RequestParam("size")  int size);
}
