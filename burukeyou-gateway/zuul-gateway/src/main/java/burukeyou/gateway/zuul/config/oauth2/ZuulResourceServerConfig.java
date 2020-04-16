package burukeyou.gateway.zuul.config.oauth2;


import burukeyou.auth.authClient.config.oauth2.CustomResourceServerConfig;
import burukeyou.gateway.zuul.filter.ZuulAuditLogFilter;
import burukeyou.gateway.zuul.filter.ZuulRateLimitFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.stereotype.Component;


@Component
@EnableResourceServer
public class ZuulResourceServerConfig extends CustomResourceServerConfig {

    @Autowired
    private ZuulExpressionHandler zuulExpressionHandler;

    /**
     *    限流 -》 认证  -》 审计日志 -》 权限控制（授权）  -》
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        super.configure(http);

        http.headers().frameOptions().sameOrigin();

        http.authorizeRequests().anyRequest()
                .access("#permissionService.hasPermission(request, authentication)")
                .and()
                .addFilterBefore(new ZuulRateLimitFilter(), SecurityContextPersistenceFilter.class)
                .addFilterBefore(new ZuulAuditLogFilter(),ExceptionTranslationFilter.class);

    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources);
        resources.expressionHandler(zuulExpressionHandler);
    }
}
