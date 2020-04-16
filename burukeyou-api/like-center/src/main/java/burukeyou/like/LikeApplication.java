package burukeyou.like;

import burukeyou.auth.authClient.annotation.EnableAuthClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@EnableAuthClient
@SpringBootApplication
public class LikeApplication {
    public static void main(String[] args) {
        SpringApplication.run(LikeApplication.class,args);
    }
}
