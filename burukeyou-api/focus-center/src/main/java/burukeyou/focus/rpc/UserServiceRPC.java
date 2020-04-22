package burukeyou.focus.rpc;

import burukeyou.common.core.entity.bo.MicroServiceName;
import burukeyou.focus.rpc.fallback.UserServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name = MicroServiceName.USER_SERVER,fallback = UserServiceFallback.class)
public interface UserServiceRPC {



}
