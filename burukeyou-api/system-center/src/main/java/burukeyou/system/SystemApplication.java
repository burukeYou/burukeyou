package burukeyou.system;

import burukeyou.auth.authClient.annotation.EnableAuthClient;
import burukeyou.common.core.entity.annotation.EnableCustomValidation;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients
@EnableAuthClient
@EnableCustomValidation
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("burukeyou.system.mapper")
public class SystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class,args);
    }
}
