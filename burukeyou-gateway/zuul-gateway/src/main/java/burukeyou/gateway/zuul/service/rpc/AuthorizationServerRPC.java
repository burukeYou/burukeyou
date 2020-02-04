package burukeyou.gateway.zuul.service.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

/**
 *      rpc鉴权服务
 */

@Service
@FeignClient(name = "authorization-server")
public interface AuthorizationServerRPC {

}
