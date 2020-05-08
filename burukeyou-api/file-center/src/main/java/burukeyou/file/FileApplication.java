package burukeyou.file;

import burukeyou.auth.authClient.annotation.EnableAuthClient;
import burukeyou.file.entity.properties.FileServerProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableDiscoveryClient
@SpringBootApplication
@EnableAuthClient
@EnableConfigurationProperties(FileServerProperties.class)
public class FileApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileApplication.class,args);
    }
}
