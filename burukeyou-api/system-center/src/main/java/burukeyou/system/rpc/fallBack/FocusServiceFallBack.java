package burukeyou.system.rpc.fallBack;

import burukeyou.common.core.entity.vo.ResultVo;
import burukeyou.system.rpc.FocusServiceRPC;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class FocusServiceFallBack implements FocusServiceRPC {

    @GetMapping("/{targetType}")
    public ResultVo<Map<String,Boolean>> judgeIsFollwerList(@PathVariable("targetType") String targetType, List<String> targetidList){
            log.error("调用判断是否关注服务熔断降级");
        return null;
    }

    @Override
    public ResultVo<Page<String>> getUserFocusTargetPage(String userId, String targetType, int page, int size) {
        log.error("调用查找用户关注服务熔断降级");
        return null;
    }

}
