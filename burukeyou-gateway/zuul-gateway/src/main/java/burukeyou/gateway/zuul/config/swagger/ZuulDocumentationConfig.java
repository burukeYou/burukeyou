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
        resources.add(swaggerResource("统一认证授权接口", zuulPrefix+"/token", "1.0"));
        resources.add(swaggerResource("全局搜索接口", zuulPrefix+"/search", "1.0"));
        resources.add(swaggerResource("后台系统接口", zuulPrefix+"/admin", "1.0"));
        resources.add(swaggerResource("用户微服务接口", zuulPrefix+"/user", "1.0"));
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
