package burukeyou.search.server.service.impl;

import burukeyou.search.server.entity.dto.QueryDto;
import burukeyou.search.server.entity.enums.IndexEnums;
import burukeyou.search.server.entity.vo.SearchResultVo;
import burukeyou.search.server.service.SearchService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    public List<SearchResultVo> searchAll(QueryDto queryDto){
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.from(queryDto.getPage());
        sourceBuilder.size(queryDto.getSize());

        if (StringUtils.isNotBlank(queryDto.getKeyword())) {
            QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(queryDto.getKeyword(), "title", "description","html_content","nickname","name");
            sourceBuilder.query(queryBuilder);
        }

        SearchRequest searchRequest;
        if (IndexEnums.exits(queryDto.getType())){
            searchRequest = new SearchRequest(queryDto.getType());
        }else {
             searchRequest = new SearchRequest();
        }
        searchRequest.source(sourceBuilder);

        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<SearchResultVo> list = new ArrayList<>();
        SearchHits hits = searchResponse.getHits();
        for (SearchHit e : hits.getHits()) {
            SearchResultVo vo = new SearchResultVo();
            Object entity = JSON.parseObject(e.getSourceAsString(), Object.class);
            vo.setType(e.getIndex());
            vo.setEntity(entity);
            list.add(vo);
        }
        return list;
    }
}
