package burukeyou.gateway.general;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplocation {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplocation.class,args);
    }
}
