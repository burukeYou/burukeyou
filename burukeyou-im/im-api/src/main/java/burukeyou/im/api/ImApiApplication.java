package burukeyou.im.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients //  开启服务调用功能
@ComponentScan({"burukeyou.auth.authClient","burukeyou.im.api","burukeyou.common.core.aspect"})
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ImApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ImApiApplication.class,args);
    }
}
