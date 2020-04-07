package burukeyou.boiling.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


//    //跨域设置
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowCredentials(true)  //是否允许用户发送、处理 cookie
//                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
//                .allowedOrigins("*")   //允许跨域
//                .allowedHeaders("*")  //允许所有的请求头
//                .maxAge(3600)  //预检请求的有效期，单位为秒。有效期内，不会重复发送预检请求
//                .exposedHeaders("newToken"); //允许前端获取后端设置的自定义header
//    }
}
