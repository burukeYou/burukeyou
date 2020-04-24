package burukeyou.like.service.impl;

import burukeyou.auth.authClient.util.AuthUtils;
import burukeyou.like.entity.pojo.AmsLike;
import burukeyou.like.mapper.LikeMapper;
import burukeyou.like.service.LikeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LikeServiceImpl extends ServiceImpl<LikeMapper, AmsLike> implements LikeService {

    @Override
    public Map<String, Boolean> judgeIsLikeList(String parentType, List<String> parentIdList) {
        if (CollectionUtils.isEmpty(parentIdList))
            return null;

        Map<String, Boolean> result = new HashMap<>();
        parentIdList.forEach(e -> {
            int count = super.count(new QueryWrapper<AmsLike>().lambda()
                    .eq(AmsLike::getParentType, parentType)
                    .eq(AmsLike::getUserId, AuthUtils.ID()).eq(AmsLike::getParentType, e));
            result.put(e, count > 0);
        });
        return result;
    }
}
