package burukeyou.like.scheduled;

import burukeyou.common.core.entity.enums.LikeParentTypeEnums;
import burukeyou.like.entity.bo.LikeCount;
import burukeyou.like.entity.pojo.AmsLike;
import burukeyou.like.service.LikeService;
import burukeyou.like.service.RedisLikeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class LikeStatusTask {

    private final RedisLikeService redisLikeService;

    private final LikeService likeService;

    public LikeStatusTask(RedisLikeService redisLikeService, LikeService likeService) {
        this.redisLikeService = redisLikeService;
        this.likeService = likeService;
    }

    /**
     *   同步点赞数据到DB
     */
    @Scheduled(cron = "30 * * * * ?" )
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void synLikeDataToDB(){
        //
        for (AmsLike e : redisLikeService.getAllLikeData()) {
            LambdaQueryWrapper<AmsLike> wrapper = new QueryWrapper<AmsLike>().lambda().eq(AmsLike::getUserId, e.getUserId())
                    .eq(AmsLike::getParentId, e.getParentId())
                    .eq(AmsLike::getParentType, e.getParentType());
            if (e.isLike()){
                if (likeService.count(wrapper) <= 0){
                    likeService.save(e);
                }
            }else {
                likeService.remove(wrapper);
            }
        }
        //
        for (LikeCount e : redisLikeService.getAllLIkeCountData()) {
            String parentType = e.getParentType();
            if (LikeParentTypeEnums.ARTICLE.VALUE().equals(parentType)){
                // todo 异步更新点赞量
            }else if (LikeParentTypeEnums.COMMENT.VALUE().equals(parentType)){

            }else if (LikeParentTypeEnums.REPLY.VALUE().equals(parentType)) {

            }else if (LikeParentTypeEnums.BOILING.VALUE().equals(parentType)){

            }
        }
    }


}
