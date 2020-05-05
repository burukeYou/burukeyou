package burukeyou.like.service.impl;

import burukeyou.auth.authClient.util.AuthUtils;
import burukeyou.like.entity.bo.LikeMsg;
import burukeyou.like.entity.pojo.AmsLike;
import burukeyou.like.mapper.LikeMapper;
import burukeyou.like.service.LikeService;
import burukeyou.like.service.MqService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class LikeServiceImpl extends ServiceImpl<LikeMapper, AmsLike> implements LikeService {

    private final MqService mqService;

    public LikeServiceImpl(MqService mqService) {
        this.mqService = mqService;
    }

    @Override
    public Map<String, Boolean> judgeIsLikeList(String parentType, List<String> parentIdList) {
        if (CollectionUtils.isEmpty(parentIdList))
            return null;

        Map<String, Boolean> result = new HashMap<>();
        parentIdList.forEach(e -> {
            int count = super.count(new QueryWrapper<AmsLike>().lambda()
                    .eq(AmsLike::getParentType, parentType)
                    .eq(AmsLike::getUserId, AuthUtils.ID()).eq(AmsLike::getParentId, e));
            result.put(e, count > 0);
        });
        return result;
    }

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public void postLike(String parentId, String parentType,boolean isLike) {
        //mqService.postLike(new AmsLike(AuthUtils.ID(),parentId,parentType));
        if (isLike)
            mqService.postLike(new LikeMsg(atomicInteger.getAndIncrement()+"",parentId,parentType,true));
        else
            mqService.postLike(new LikeMsg(atomicInteger.getAndIncrement()+"",parentId,parentType,false));
    }

}
