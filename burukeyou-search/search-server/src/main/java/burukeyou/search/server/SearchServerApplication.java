package burukeyou.search.server;

import burukeyou.auth.authClient.annotation.EnableAuthClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)  //开启方法级别的权限拦截
@EnableAuthClient
@EnableDiscoveryClient
public class SearchServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchServerApplication.class,args);
    }
}
