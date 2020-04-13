package burukeyou.auth.authClient.config.oauth2;

import burukeyou.auth.authClient.config.AuthClientProperties;
import burukeyou.auth.authClient.handler.CustomAccessDeniedHandler;
import burukeyou.auth.authClient.handler.CustomAuthenticationEntryPoint;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;


/**
 *  资源服务器配置
 *
 * @author burukeyou
 *
 */
@Slf4j
@Configuration
@EnableResourceServer
@ComponentScan("burukeyou.auth.authClient")
public class CustomResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private CustomAuthenticationEntryPoint baseAuthenticationEntryPoint;

    @Autowired
    private AuthClientProperties authClientProperties;

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    // 注入密码加密 （供资源服务器调用）
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        // todo 被调用两次 -- 待解决
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // 放行 swagger ui
        http.authorizeRequests().antMatchers("/v2/api-docs", "/swagger-resources/configuration/ui",
                "/swagger-resources","/swagger-resources/configuration/security",
                "/swagger-ui.html","/course/coursebase/**","/webjars/**","/api/**/v2/api-docs").permitAll();

        if (authClientProperties.getIgnoreUrls() != null){
            for(String url: authClientProperties.getIgnoreUrls()){
                http.authorizeRequests().antMatchers(url).permitAll();
            }
        }

        http.authorizeRequests().anyRequest().authenticated();
       // http.authorizeRequests().anyRequest().permitAll();

    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        if (authClientProperties.getResourceId() != null)
            resources.resourceId(authClientProperties.getResourceId());

        // 这里的签名key 保持和认证中心一致
        if (authClientProperties.getSigningKey() == null)
            log.info("SigningKey is null cant not decode token.......");

        //
        DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
        accessTokenConverter.setUserTokenConverter(new CustomUserAuthenticationConverter());


        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(authClientProperties.getSigningKey());
        converter.setVerifier(new MacSigner(authClientProperties.getSigningKey()));

        //
        CustomTokenServices tokenServices = new CustomTokenServices();

        //
        tokenServices.setTokenStore(new JwtTokenStore(converter));
        tokenServices.setJwtAccessTokenConverter(converter);
        tokenServices.setDefaultAccessTokenConverter(accessTokenConverter);

        //
        resources.tokenServices(tokenServices)
                 .authenticationEntryPoint(baseAuthenticationEntryPoint)
                 .accessDeniedHandler(customAccessDeniedHandler);
    }
}
