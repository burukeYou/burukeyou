package burukeyou.article;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@ComponentScan({"burukeyou.auth.authClient","burukeyou.article","burukeyou.common.core.aspect"})
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ArticleCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(ArticleCenterApplication.class,args);
    }
}
