package burukeyou.focus.service.impl;

import burukeyou.auth.authClient.util.AuthUtils;
import burukeyou.focus.entity.bo.FocusConstant;
import burukeyou.focus.entity.dto.LikeCount;
import burukeyou.focus.entity.enums.FocusStatusEnum;
import burukeyou.focus.entity.enums.FocusTargetEnums;
import burukeyou.focus.entity.pojo.UmsFocus;
import burukeyou.focus.rpc.SystemServerRPC;
import burukeyou.focus.service.RedisFocusService;
import burukeyou.focus.service.UmsFocusService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 *        targetId - like count
 *        用户Id::被点赞targetId::被点赞targetType  -- true
 */
@Slf4j
@Service
public class RedisFocusServiceImpl implements RedisFocusService {

    @Autowired
    private  RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private  UmsFocusService umsFocusService;

    @Autowired
    private SystemServerRPC systemServerRPC;

    @Override
    public void focus(String userId,String targetId,String targetType){
        String focusStatusKey = FocusConstant.buildFocusStatusKey(AuthUtils.ID(), targetId, targetType);
        redisTemplate.opsForHash().put(FocusConstant.FOCUS_STATUS_KEY,focusStatusKey, FocusStatusEnum.LIKE.VALUE());

        String focusCountKey = FocusConstant.bulidFocusCountKey(targetId, targetType);
        redisTemplate.opsForHash().increment(FocusConstant.FOCUS_COUNT_KEY,focusCountKey,1);
    }

    @Override
    public void cancelFocus(String userId,String targetId,String targetType){
        String focusStatusKey = FocusConstant.buildFocusStatusKey(AuthUtils.ID(), targetId, targetType);
        redisTemplate.opsForHash().put(FocusConstant.FOCUS_STATUS_KEY,focusStatusKey, FocusStatusEnum.UNLIKE.VALUE());

        String focusCountKey = FocusConstant.bulidFocusCountKey(targetId, targetType);
        redisTemplate.opsForHash().increment(FocusConstant.FOCUS_COUNT_KEY,focusCountKey,-1);
    }

    @Override
    public List<UmsFocus> getAllFocusData(){
        Cursor<Map.Entry<Object,Object>> cursor = redisTemplate.opsForHash().scan(FocusConstant.FOCUS_STATUS_KEY, ScanOptions.NONE);

        List<UmsFocus> umsFocusList = new ArrayList<>();
        while (cursor.hasNext()){
            Map.Entry<?, ?> current = cursor.next();
            String key = (String) current.getKey();
            String[] keys = key.split("::");

            Boolean value = (Boolean)current.getValue();
            if (keys.length == 3){
                umsFocusList.add(UmsFocus.builder().userId(keys[0]).targetId(keys[1]).targetType(keys[2]).focus(value).build());
                // delete cache
                redisTemplate.opsForHash().delete(FocusConstant.FOCUS_STATUS_KEY,key);
            }
        }


        return umsFocusList;
    }

    public List<LikeCount>  getAllFoucusCountData(){
        Cursor<Map.Entry<Object,Object>> cursor = redisTemplate.opsForHash().scan(FocusConstant.FOCUS_COUNT_KEY, ScanOptions.NONE);

        List<LikeCount> likeCountsList = new ArrayList<>();
        while (cursor.hasNext()) {
            Map.Entry<?, ?> current = cursor.next();
            String key = (String) current.getKey();
            String[] keys = key.split("::");

            if (keys.length > 0) {
                likeCountsList.add(LikeCount.builder().targetId(keys[0]).targetType(keys[1]).count((Integer) current.getValue()).build());
                // delete cache
                redisTemplate.opsForHash().delete(FocusConstant.FOCUS_COUNT_KEY,key);
            }
        }
        return likeCountsList;
    }


    @Override
    public void focusStatusDataSyncToDB(){
        for (UmsFocus focus :getAllFocusData()) {
            System.out.println("同步关注数据(关注/取消关注):"+focus);
            LambdaQueryWrapper<UmsFocus> wrapper = new QueryWrapper<UmsFocus>().lambda().eq(UmsFocus::getUserId, focus.getUserId())
                    .eq(UmsFocus::getTargetType, focus.getTargetType()).eq(UmsFocus::getTargetId, focus.getTargetId());
            if (focus.isFocus()){
                // 添加/更新关注
               if (umsFocusService.count(wrapper) <= 0){
                   umsFocusService.save(focus);
               }
            }else {
                // 删除关注记录
                umsFocusService.remove(wrapper);
            }
        }
    }

    @Override
    public void focusCountDataSyncToDB() {
        for (LikeCount likeCount : getAllFoucusCountData()) {
            String targetType = likeCount.getTargetType();
            System.out.println("同步数量:"+likeCount);
            if (FocusTargetEnums.TOPIC.VALUE().equals(targetType)){
                    // 话题关注数减少
            }else if (FocusTargetEnums.LABEL.VALUE().equals(targetType)){
                    // 标签关注数减少
                    systemServerRPC.updateFoucusCount(likeCount.getTargetId(),likeCount.getCount());
            }else if (FocusTargetEnums.USER.VALUE().equals(targetType)){
                    // 用户粉丝减少
            }
        }
    }


}
