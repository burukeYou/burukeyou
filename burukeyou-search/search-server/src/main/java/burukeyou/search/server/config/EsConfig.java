package burukeyou.search.server.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EsConfig {

    private String host;

    private int port;


    @Bean
    public RestHighLevelClient restHighLevelClient(){
        RestClientBuilder http = RestClient.builder(new HttpHost(host, port, "http"));
        return new RestHighLevelClient(http);
    }


}
