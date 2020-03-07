package burukeyou.article;

import burukeyou.auth.authClient.annotation.EnableAuthClient;
import burukeyou.common.core.entity.annotation.EnableCustomValidation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
//@ComponentScan({"burukeyou.auth.authClient","burukeyou.article","burukeyou.common.core.aspect"})
@EnableAuthClient
@EnableCustomValidation
@EnableDiscoveryClient
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ArticleCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(ArticleCenterApplication.class,args);
    }
}
