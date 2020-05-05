package burukeyou.like.service.impl;

import burukeyou.like.entity.bo.LikeConstant;
import burukeyou.like.entity.bo.LikeCount;
import burukeyou.like.entity.enums.LikeStatusEnum;
import burukeyou.like.entity.pojo.AmsLike;
import burukeyou.like.service.RedisLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RedisLikeServiceImpl implements RedisLikeService {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public void postLike(String userId, String targetId, String targetType) {
        String likeStatusKey = LikeConstant.buildLikeStatusKey(userId, targetId, targetType);

        // 保证幂等性 todo update
        if (redisTemplate.opsForHash().hasKey(LikeConstant.LIKE_STATUS_KEY, likeStatusKey) &&
                !((boolean)redisTemplate.opsForHash().get(LikeConstant.LIKE_STATUS_KEY, likeStatusKey)))
            return;

        String likeCountKey = LikeConstant.bulidLikeCountKey(targetId, targetType);
        redisTemplate.opsForHash().increment(LikeConstant.LIKE_COUNT_KEY,likeCountKey,1);
        redisTemplate.opsForHash().put(LikeConstant.LIKE_STATUS_KEY, likeStatusKey, LikeStatusEnum.LIKE.VALUE());
    }

    @Override
    public void cancelLike(String userId, String targetId, String targetType) {
        String likeStatusKey = LikeConstant.buildLikeStatusKey(userId, targetId, targetType);
        // 保证幂等性
        if (redisTemplate.opsForHash().hasKey(LikeConstant.LIKE_STATUS_KEY, likeStatusKey)  &&
                !((boolean)redisTemplate.opsForHash().get(LikeConstant.LIKE_STATUS_KEY, likeStatusKey)))
            return;

        String likeCountKey = LikeConstant.bulidLikeCountKey(targetId, targetType);
        redisTemplate.opsForHash().increment(LikeConstant.LIKE_COUNT_KEY,likeCountKey,-1);
        redisTemplate.opsForHash().put(LikeConstant.LIKE_STATUS_KEY, likeStatusKey, LikeStatusEnum.UNLIKE.VALUE());
    }

    @Override
    public List<AmsLike> getAllLikeData() {
        Cursor<Map.Entry<Object,Object>> cursor = redisTemplate.opsForHash().scan(LikeConstant.LIKE_STATUS_KEY, ScanOptions.NONE);

        List<AmsLike> amsLikeList = new ArrayList<>();
        while (cursor.hasNext()){
            Map.Entry<?, ?> current = cursor.next();
            String key = (String) current.getKey();
            String[] keys = key.split("::");

            Boolean value = (Boolean)current.getValue();
            if (keys.length == 3){
                amsLikeList.add(AmsLike.builder().userId(keys[0]).parentId(keys[1]).parentType(keys[2]).like(value).build());
                // delete cache
                redisTemplate.opsForHash().delete(LikeConstant.LIKE_STATUS_KEY,key);
            }
        }
        return amsLikeList;
    }

    @Override
    public List<LikeCount> getAllLIkeCountData() {
        Cursor<Map.Entry<Object,Object>> cursor = redisTemplate.opsForHash().scan(LikeConstant.LIKE_COUNT_KEY, ScanOptions.NONE);

        List<LikeCount> likeCountsList = new ArrayList<>();
        while (cursor.hasNext()) {
            Map.Entry<?, ?> current = cursor.next();
            String key = (String) current.getKey();
            String[] keys = key.split("::");

            if (keys.length > 0) {
                likeCountsList.add(LikeCount.builder().parentId(keys[0]).parentType(keys[1])
                        .count((Integer) current.getValue()).build());
                // delete cache
                redisTemplate.opsForHash().delete(LikeConstant.LIKE_COUNT_KEY,key);
            }
        }
        return likeCountsList;
    }
}
