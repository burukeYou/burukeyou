package burukeyou.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan({"burukeyou.auth.authClient","burukeyou.user","burukeyou.common.core.aspect"})
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UmsUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UmsUserApplication.class,args);
    }
}
