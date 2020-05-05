package burukeyou.like.service;

import burukeyou.like.entity.bo.LikeCount;
import burukeyou.like.entity.pojo.AmsLike;

import java.util.List;

public interface RedisLikeService {

    /**
     *      点赞
     * @param userId
     * @param targetId
     * @param targetType
     */
    void postLike(String userId,String targetId,String targetType);

    /**
     *  取消点赞
     * @param userId
     * @param targetId
     * @param targetType
     */
    void cancelLike(String userId,String targetId,String targetType);


    /**
     *     获得所有点赞数据
     * @return
     */
    List<AmsLike> getAllLikeData();

    /**
     *      获得所有点赞数量
     * @return
     */
    List<LikeCount>  getAllLIkeCountData();


}
