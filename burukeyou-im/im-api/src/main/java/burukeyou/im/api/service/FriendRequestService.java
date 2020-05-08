package burukeyou.im.api.service;

import burukeyou.im.api.enity.pojo.ImsFriendRequest;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface FriendRequestService extends IService<ImsFriendRequest> {

    /**
     *  判断是否能发送好友请求
     * @param accept_user_id 接收好友请求的用户id
     * @return
     */
    Integer isCanSend(String accept_user_id);

    /**
     *   add
     * @param imsFriendRequest
     */
    boolean sendFriendRequest(ImsFriendRequest imsFriendRequest);

    /**
     *    find the accept friend request list of user
     * @param userId
     * @return
     */
    List<ImsFriendRequest> getList(String userId);

    /**
     *
     * @param sendUserId
     * @param operationType
     */
    boolean updateFriendRequetsState(String sendUserId, Integer operationType);

    /**
     *
     * @param sendUserId
     * @param operationType
     * @return
     */
    boolean passFriendRequest(String sendUserId, String sendUserNickname,String sendUserAvatar,Integer operationType);
}
