package burukeyou.focus.rpc;

import burukeyou.common.core.entity.bo.MicroServiceName;
import burukeyou.common.core.entity.vo.ResultVo;
import burukeyou.focus.rpc.fallback.SystemServerFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = MicroServiceName.SYSTEM_SERVER,fallback = SystemServerFallback.class)
public interface SystemServerRPC {

    @PutMapping("/label/{id}")
    ResultVo updateFoucusCount(@PathVariable("id")String id, @RequestParam("count") int count);

}
