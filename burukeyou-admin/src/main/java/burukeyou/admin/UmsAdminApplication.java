package burukeyou.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableDiscoveryClient
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan("burukeyou.auth.authClient")
public class UmsAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(UmsAdminApplication.class,args);
    }
}



