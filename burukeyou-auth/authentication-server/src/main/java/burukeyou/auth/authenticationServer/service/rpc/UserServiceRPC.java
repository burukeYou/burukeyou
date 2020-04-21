package burukeyou.auth.authenticationServer.service.rpc;

import burukeyou.auth.authenticationServer.entity.pojo.UmsUser;
import burukeyou.auth.authenticationServer.service.rpc.fallback.UserServiceFallback;
import burukeyou.common.core.entity.bo.MicroServiceName;
import burukeyou.common.core.entity.vo.ResultVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = MicroServiceName.USER_SERVER,fallback = UserServiceFallback.class)
public interface UserServiceRPC {

    @GetMapping("/user/uniqueId")
    ResultVo<UmsUser> getUserByUniqueId(@RequestParam String uniqueId);

}
