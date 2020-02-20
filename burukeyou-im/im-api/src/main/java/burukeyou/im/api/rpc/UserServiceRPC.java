package burukeyou.im.api.rpc;

import burukeyou.common.core.entity.vo.ResultVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-server")
public interface UserServiceRPC {


    @GetMapping("/uniqueId")
    ResultVo getUserByUniqueId(@RequestParam String uniqueId);



}
