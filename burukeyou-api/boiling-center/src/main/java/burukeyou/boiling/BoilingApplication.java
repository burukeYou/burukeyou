package burukeyou.boiling;

import burukeyou.auth.authClient.annotation.EnableAuthClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
@EnableAuthClient
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BoilingApplication {
    public static void main(String[] args) {
        SpringApplication.run(BoilingApplication.class,args);
    }
}
