package burukeyou.im.api.service;


import burukeyou.im.api.enity.pojo.ImsFriendRelation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface FriendRelationService extends IService<ImsFriendRelation> {

    /**
     *  删除双向好友关系
     * @param friendId
     * @return
     */
    boolean deleteFriendRelation(String friendId);

    /**
     *     获取用户好友列表
     * @param id
     * @return
     */
    List<ImsFriendRelation> getFriendListByUserId(String id);
}
