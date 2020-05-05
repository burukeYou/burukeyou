package burukeyou.like.service;

import burukeyou.like.entity.bo.LikeMsg;

public interface MqService {

    /**
     *      点赞
     * @param like
     */
    void postLike(LikeMsg like);
}
