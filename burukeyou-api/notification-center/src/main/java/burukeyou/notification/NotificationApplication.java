package burukeyou.notification;


import burukeyou.auth.authClient.annotation.EnableAuthClient;
import burukeyou.common.core.entity.annotation.EnableCustomValidation;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan("burukeyou.notification.mapper")
@EnableCustomValidation
@EnableDiscoveryClient
@EnableAuthClient
@SpringBootApplication
public class NotificationApplication {


    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class,args);
    }


}
