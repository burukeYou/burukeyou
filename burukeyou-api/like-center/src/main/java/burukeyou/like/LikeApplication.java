package burukeyou.like;

import burukeyou.auth.authClient.annotation.EnableAuthClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableScheduling
@EnableDiscoveryClient
@EnableAuthClient
@SpringBootApplication
@MapperScan("burukeyou.like.mapper")
public class LikeApplication {
    public static void main(String[] args) {
        SpringApplication.run(LikeApplication.class,args);
    }
}
