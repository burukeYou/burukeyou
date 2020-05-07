package burukeyou.search.server.config;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EsConfig {

    @Value("${custom.es.hostname}")
    private String host;

    @Value("${custom.es.port}")
    private int port;

    @Bean
    public RestClient restClient(){
        RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost(host, port, "http"));
        Header[] defaultHeaders = {new BasicHeader("content-type", "application/json")};
        restClientBuilder.setDefaultHeaders(defaultHeaders);
        return restClientBuilder.build();
    }

    @Bean
    public RestHighLevelClient restHighLevelClient(){
        RestClientBuilder http = RestClient.builder(new HttpHost(host, port, "http"));
        return new RestHighLevelClient(http);
    }


}
