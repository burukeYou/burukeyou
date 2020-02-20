package burukeyou.im.api.service;


import burukeyou.im.api.enity.pojo.ImsFriendRelation;
import com.baomidou.mybatisplus.extension.service.IService;

public interface FriendRelationService extends IService<ImsFriendRelation> {

    /**
     *  删除双向好友关系
     * @param friendId
     * @return
     */
    boolean deleteFriendRelation(String friendId);
}
