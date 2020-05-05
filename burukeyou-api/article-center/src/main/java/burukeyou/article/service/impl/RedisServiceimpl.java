package burukeyou.article.service.impl;

import burukeyou.article.entity.bo.RedisKeyConstant;
import burukeyou.article.entity.bo.VisitCount;
import burukeyou.article.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RedisServiceimpl implements RedisService {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public void incrArticleVisitCount(String articleId) {
        if (StringUtils.isNotBlank(articleId))
            redisTemplate.opsForHash().increment(RedisKeyConstant.VISIT_COUNT_KEY,articleId,1);
    }

    public List<VisitCount> getAllFoucusCountData(){
        //  ScanOptions.scanOptions().match("*").count(1000).build()
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(RedisKeyConstant.VISIT_COUNT_KEY, ScanOptions.NONE);
        List<VisitCount> likeCountsList = new ArrayList<>();
        while (cursor.hasNext()){
            Map.Entry<Object, Object> next = cursor.next();
            String key = (String)next.getKey();
            Integer value = (Integer) next.getValue();
            likeCountsList.add(new VisitCount(key,value));
            redisTemplate.opsForHash().delete(RedisKeyConstant.VISIT_COUNT_KEY,key);
        }
        return likeCountsList;
    }
}
