package burukeyou.focus.rpc.fallback;

import burukeyou.common.core.entity.vo.ResultVo;
import burukeyou.focus.rpc.SystemServerRPC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SystemServerFallback implements SystemServerRPC {
    @Override
    public ResultVo updateFoucusCount(String id, int count) {
        log.error("修改标签：{} 的数量失败",id);
        return null;
    }
}
