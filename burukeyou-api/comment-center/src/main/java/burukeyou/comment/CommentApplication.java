package burukeyou.comment;

import burukeyou.auth.authClient.annotation.EnableAuthClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableAsync
@SpringBootApplication
@EnableDiscoveryClient
@EnableAuthClient
public class CommentApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommentApplication.class,args);
    }
}
