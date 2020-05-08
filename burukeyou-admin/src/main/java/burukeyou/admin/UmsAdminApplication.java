package burukeyou.admin;

import burukeyou.auth.authClient.annotation.EnableAuthClient;
import burukeyou.common.log.annotation.EnableAuditLog;
import burukeyou.common.log.aspect.AuditLogAOP;
import burukeyou.common.log.properties.AuditLogProperties;
import burukeyou.common.log.service.AuditLogService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableAuthClient
@EnableFeignClients
@EnableAuditLog
@EnableSwagger2
public class UmsAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(UmsAdminApplication.class,args);
    }
}



