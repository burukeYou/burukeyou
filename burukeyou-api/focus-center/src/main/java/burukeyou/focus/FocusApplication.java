package burukeyou.focus;

import burukeyou.auth.authClient.annotation.EnableAuthClient;
import burukeyou.common.core.entity.annotation.EnableCustomValidation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableCustomValidation
@EnableDiscoveryClient
@EnableAuthClient
@SpringBootApplication
public class FocusApplication {

    public static void main(String[] args) {
        SpringApplication.run(FocusApplication.class,args);
    }

}
