package burukeyou.auth.authenticationServer.service.rpc.fallback;

import burukeyou.auth.authenticationServer.entity.pojo.UmsUser;
import burukeyou.auth.authenticationServer.service.rpc.UserServiceRPC;
import burukeyou.common.core.entity.bo.MicroServiceName;
import burukeyou.common.core.entity.vo.ResultVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Component
public class UserServiceFallback implements UserServiceRPC {

    @GetMapping("/uniqueId")
    public ResultVo<UmsUser> getUserByUniqueId(@RequestParam String uniqueId){
        log.error("调用用户服务异常");
        return null;
    }

}
