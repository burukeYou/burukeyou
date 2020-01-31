package burukeYou.config.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 *  资源服务器配置
 *
 * @author burukeyou
 *
 */
@Configuration
@EnableResourceServer
public class Oauth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("burukeyou-user");

        //
        DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
        accessTokenConverter.setUserTokenConverter(new CustomUserAuthenticationConverter());

        CustomTokenServices tokenServices = new CustomTokenServices();

        // 这里的签名key 保持和认证中心一致
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("909090");
        converter.setVerifier(new MacSigner("909090"));

        //
        JwtTokenStore jwtTokenStore = new JwtTokenStore(converter);
        tokenServices.setTokenStore(jwtTokenStore);
        tokenServices.setJwtAccessTokenConverter(converter);
        tokenServices.setDefaultAccessTokenConverter(accessTokenConverter);

        resources.tokenServices(tokenServices).authenticationEntryPoint(customAuthenticationEntryPoint);
    }


}
