package burukeyou.focus.rpc;

import burukeyou.common.core.entity.bo.MicroServiceName;
import burukeyou.common.core.entity.vo.ResultVo;
import burukeyou.focus.entity.vo.UserSearchVo;
import burukeyou.focus.rpc.fallback.UserServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(name = MicroServiceName.USER_SERVER,fallback = UserServiceFallback.class)
public interface UserServiceRPC {

    @GetMapping("/user/idlist")
    ResultVo<List<UserSearchVo>> getMiniUserByIdList( @RequestParam("idList") List<String> idList);

}
