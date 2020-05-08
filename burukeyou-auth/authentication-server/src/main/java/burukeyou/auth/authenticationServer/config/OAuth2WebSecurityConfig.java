package burukeyou.auth.authenticationServer.config;

import burukeyou.auth.authenticationServer.ouath2.Ouath2UserDetailService;
import burukeyou.auth.authenticationServer.ouath2.handler.OAuth2LogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/** spring security 安全配置
 *
 * @author burukeyou
 *
 */
@EnableWebSecurity
@Configuration
public class OAuth2WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private  Ouath2UserDetailService ouath2UserDetailService;

  /*  @Autowired
    private  OAuth2LogoutSuccessHandler oAuth2LogoutSuccessHandler;*/


    // 重新注入认证管理器
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // 注入密码加密
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //=================================配置==================================================================
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(ouath2UserDetailService) // 使用自定义方式加载用户信息
                .passwordEncoder(passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/v2/api-docs", "/swagger-resources/configuration/ui",
                "/swagger-resources","/swagger-resources/configuration/security",
                "/swagger-ui.html","/course/coursebase/**","/webjars/**","/api/v1/**/v2/api-docs").permitAll();

        //http.authorizeRequests().anyRequest().authenticated();
       // http.authorizeRequests().anyRequest().permitAll();
        //http.httpBasic();
              /*  .and()
                .formLogin();//.and()*/
                //.httpBasic();//.and()
                //.logout().logoutSuccessHandler(oAuth2LogoutSuccessHandler);
    }
}
