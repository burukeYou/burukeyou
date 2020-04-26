package burukeyou.article.rpc;

import burukeyou.article.rpc.fallbackFactory.UserServiceFallbackFactory;
import burukeyou.common.core.entity.bo.MicroServiceName;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = MicroServiceName.USER_SERVER,fallbackFactory = UserServiceFallbackFactory.class)
public interface UserServiceRPC {

    @GetMapping("/collection/judge/{collectionType}/{collectionId}")
    String judgeIsFavorities(@PathVariable("collectionType") String collectionType, @PathVariable("collectionId") String collectionId);


}
