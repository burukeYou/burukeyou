package burukeyou.search.server.utils;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;


import java.io.IOException;

public class EsUtils{

    private RestHighLevelClient restHighLevelClient;

    private  String INDEX;

    private static final String TYPE = "_doc";

    private EsUtils(){}

    public EsUtils(String INDEX) {
        this.INDEX = INDEX;
    }

    public EsUtils(String INDEX,RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
        this.INDEX = INDEX;
    }

    public <T> void insert(T dto)  {
        try {
            IndexRequest indexRequest = new IndexRequest(INDEX,TYPE);
            String source = JSON.toJSONString(dto);
            indexRequest.source(source, XContentType.JSON);

            IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
            System.out.println(indexResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
