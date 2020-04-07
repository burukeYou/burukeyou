package burukeyou.search.admin;

import burukeyou.auth.authClient.annotation.EnableAuthClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableAuthClient
@EnableDiscoveryClient
@SpringBootApplication
public class SearchAdminApplicaiton {
    public static void main(String[] args) {
        SpringApplication.run(SearchAdminApplicaiton.class,args);
    }
}
