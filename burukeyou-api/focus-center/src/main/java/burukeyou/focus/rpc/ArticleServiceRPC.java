package burukeyou.focus.rpc;

import burukeyou.common.core.entity.bo.MicroServiceName;
import burukeyou.focus.rpc.fallback.ArticleServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = MicroServiceName.ARTICLE_SERVER,fallback = ArticleServiceFallback.class)
public interface ArticleServiceRPC {

}
