package burukeyou.article.rpc.fallbackFactory;

import burukeyou.article.rpc.UserServiceRPC;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserServiceFallbackFactory implements FallbackFactory<UserServiceRPC> {
    @Override
    public UserServiceRPC create(Throwable throwable) {
        return new UserServiceRPC(){
            @Override
            public String judgeIsFavorities(String collectionType, String collectionId) {
                log.error("调用系统服务异常",throwable);
                return null;
            }
        };
    }
}
