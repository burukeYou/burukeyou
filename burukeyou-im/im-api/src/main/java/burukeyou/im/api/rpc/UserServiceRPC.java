package burukeyou.im.api.rpc;

import burukeyou.common.core.entity.bo.MicroServiceName;
import burukeyou.common.core.entity.vo.ResultVo;
import burukeyou.im.api.enity.bo.UmsUsers;
import burukeyou.im.api.rpc.fallback.UserServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = MicroServiceName.USER_SERVER,fallback = UserServiceFallback.class)
public interface UserServiceRPC {

    @GetMapping("/user/uniqueId")
    ResultVo<UmsUsers> getUserByUniqueId(@RequestParam("uniqueId") String uniqueId);
}
