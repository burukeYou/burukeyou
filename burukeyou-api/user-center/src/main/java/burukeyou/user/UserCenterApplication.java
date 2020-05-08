package burukeyou.user;

import burukeyou.auth.authClient.annotation.EnableAuthClient;
import burukeyou.common.core.entity.annotation.EnableCustomValidation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
@EnableAuthClient
@EnableCustomValidation
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserCenterApplication.class,args);
    }

}
