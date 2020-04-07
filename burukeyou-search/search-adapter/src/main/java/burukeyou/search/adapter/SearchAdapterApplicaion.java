package burukeyou.search.adapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SearchAdapterApplicaion {
    public static void main(String[] args) {
        SpringApplication.run(SearchAdapterApplicaion.class,args);
    }
}
