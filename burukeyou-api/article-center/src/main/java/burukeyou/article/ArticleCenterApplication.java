package burukeyou.article;

import burukeyou.auth.authClient.annotation.EnableAuthClient;
import burukeyou.common.core.entity.annotation.EnableCustomValidation;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableScheduling
@EnableFeignClients
@SpringBootApplication
@EnableAuthClient
@EnableCustomValidation
@EnableDiscoveryClient
@EnableGlobalMethodSecurity(prePostEnabled = true)
@MapperScan("burukeyou.article.mapper")
public class ArticleCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(ArticleCenterApplication.class,args);
    }
}
