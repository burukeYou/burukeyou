package burukeyou.auth.authenticationServer.config;

import burukeyou.auth.authenticationServer.ouath2.Ouath2UserDetailService;
import burukeyou.auth.authenticationServer.ouath2.enhancer.CustomTokenEnhancer;
import burukeyou.auth.authenticationServer.ouath2.handler.CustomAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.sql.DataSource;
import java.util.Arrays;

/** ouath2 认证服务器配置
 *
 * @author burukeyou
 *
 */
@Configuration
@EnableAuthorizationServer
public class Ouath2AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;

    private final DataSource dataSource;

    private Ouath2UserDetailService ouath2UserDetailService;

    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    public Ouath2AuthServerConfig(AuthenticationManager authenticationManager, DataSource dataSource,
                                  Ouath2UserDetailService ouath2UserDetailService,
                                CustomAuthenticationEntryPoint customAuthenticationEntryPoint) {
        this.authenticationManager = authenticationManager;
        this.dataSource = dataSource;
        this.ouath2UserDetailService = ouath2UserDetailService;
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
    }

    // 存储token的策略，默认是内存策略,修改为jwt
    @Bean
    public TokenStore tokenStore() {
        //return new JdbcTokenStore(dataSource);  基于session认证
        return new JwtTokenStore(jwtAccessTokenConverter());  //基于token认证
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter jat = new JwtAccessTokenConverter();
        jat.setSigningKey("909090"); // 使用这个key来签名，验证token的服务也使用这个key来验证
        return jat;
    }

    @Bean
    public TokenEnhancer customTokenEnhancer() {
        return new CustomTokenEnhancer();
    }


    /** =================================配置=============================================================
     *  1.
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
    }


    /**
     *      2.找auth-server申请令牌或者校验令牌时一些安全配置
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("isAuthenticated()")
                // 认证中心往外面暴露的一个用来获取jwt的SigningKey的服务/oauth/token_key，但只有认证过服务才可以
                .tokenKeyAccess("isAuthenticated()") //
                .authenticationEntryPoint(customAuthenticationEntryPoint); //自定义认证失败处理器
    }


    /**
     *  3.
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 将增强的token设置到增强链中
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        enhancerChain.setTokenEnhancers(Arrays.asList(customTokenEnhancer(), jwtAccessTokenConverter()));

        endpoints.tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .userDetailsService(ouath2UserDetailService)
                .tokenEnhancer(enhancerChain);
    }
}
