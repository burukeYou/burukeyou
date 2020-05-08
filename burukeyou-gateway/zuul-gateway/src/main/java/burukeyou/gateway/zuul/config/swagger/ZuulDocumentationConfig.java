package burukeyou.gateway.zuul.config.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Primary
@Component
public class ZuulDocumentationConfig implements SwaggerResourcesProvider {

    @Value("${zuul.prefix}")
    private String zuulPrefix;

    @Override
    public List<SwaggerResource> get() {
        List resources = new ArrayList<>();
        //v2/api-docs
        resources.add(swaggerResource("认证授权中心接口", zuulPrefix+"/token/v2/api-docs", "1.0"));
        resources.add(swaggerResource("全局搜索接口", zuulPrefix+"/search/v2/api-docs", "1.0"));
        resources.add(swaggerResource("后台系统接口", zuulPrefix+"/admin/v2/api-docs", "1.0"));
        resources.add(swaggerResource("用户服务接口", zuulPrefix+"/user/v2/api-docs", "1.0"));
        resources.add(swaggerResource("系统中心服务接口", zuulPrefix+"/system/v2/api-docs", "1.0"));
        resources.add(swaggerResource("文章中心服务接口", zuulPrefix+"/article/v2/api-docs", "1.0"));
        resources.add(swaggerResource("关注服务接口", zuulPrefix+"/focus/v2/api-docs", "1.0"));
        resources.add(swaggerResource("沸点服务接口", zuulPrefix+"/boiling/v2/api-docs", "1.0"));
        resources.add(swaggerResource("评论服务接口", zuulPrefix+"/comment/v2/api-docs", "1.0"));
        resources.add(swaggerResource("好友服务接口", zuulPrefix+"/friend/v2/api-docs", "1.0"));
        resources.add(swaggerResource("消息推送服务接口", zuulPrefix+"/msgPush/v2/api-docs", "1.0"));
        resources.add(swaggerResource("通知服务接口", zuulPrefix+"/notification/v2/api-docs", "1.0"));
        resources.add(swaggerResource("点赞服务接口", zuulPrefix+"/like/v2/api-docs", "1.0"));
        resources.add(swaggerResource("文件中心服务接口", zuulPrefix+"/file/v2/api-docs", "1.0"));
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}
