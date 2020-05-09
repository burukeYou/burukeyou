package burukeyou.focus.rpc.fallback;


import burukeyou.common.core.entity.vo.ResultVo;
import burukeyou.focus.entity.vo.UserSearchVo;
import burukeyou.focus.rpc.UserServiceRPC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class UserServiceFallback implements UserServiceRPC {

    @Override
    public ResultVo<List<UserSearchVo>> getMiniUserByIdList(List<String> idList) {
        log.error("调用用户服务异常");
        return null;
    }
}
