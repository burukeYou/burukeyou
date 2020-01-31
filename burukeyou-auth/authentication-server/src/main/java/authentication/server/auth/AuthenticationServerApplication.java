package authentication.server.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient  // 开服务注册和发现到nacos注册中心
@EnableFeignClients //  开启服务调用功能
public class AuthenticationServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthenticationServerApplication.class,args);
    }
}
