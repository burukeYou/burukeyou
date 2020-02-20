package burukeyou.im.api.service.impl;

import burukeyou.auth.authClient.util.AuthUtils;
import burukeyou.common.core.entity.vo.ResultVo;
import burukeyou.im.api.enity.bo.UmsUsers;
import burukeyou.im.api.enity.enums.FriendRequestStateEnum;
import burukeyou.im.api.enity.enums.IsCanSendFriendRequestEnum;
import burukeyou.im.api.enity.pojo.ImsFriendRelation;
import burukeyou.im.api.enity.pojo.ImsFriendRequest;
import burukeyou.im.api.mapper.FriendRequestMapper;
import burukeyou.im.api.rpc.UserServiceRPC;
import burukeyou.im.api.service.FriendRelationService;
import burukeyou.im.api.service.FriendRequestService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class FriendRequestServiceImpl extends ServiceImpl<FriendRequestMapper,ImsFriendRequest> implements FriendRequestService {

    private final UserServiceRPC userServiceRPC;

    private final FriendRelationService friendRelationService;

    public FriendRequestServiceImpl(UserServiceRPC userServiceRPC, FriendRelationService friendRelationService) {
        this.userServiceRPC = userServiceRPC;
        this.friendRelationService = friendRelationService;
    }

    @Override
    public Integer isCanSend(String accept_user_id) {
        // 不能添加自己
        if (accept_user_id.equals(AuthUtils.ID()))
            return IsCanSendFriendRequestEnum.NOT_YOURSELF.STATE;

        // 添加的用户不存在
        if (userServiceRPC.getUserByUniqueId(accept_user_id).getData() == null)
            return IsCanSendFriendRequestEnum.USER_NOT_EXIST.STATE;

        // 判断是否是好友关系
        int count = friendRelationService.count(new QueryWrapper<ImsFriendRelation>().lambda()
                .eq(ImsFriendRelation::getUserId, AuthUtils.ID()).eq(ImsFriendRelation::getFriendId, accept_user_id));

        if (count <= 0) {
            return IsCanSendFriendRequestEnum.ALREADY_FRIENDS.STATE;
        }

        return IsCanSendFriendRequestEnum.SUCCESS.STATE;
    }

    @Override
    public boolean sendFriendRequest(ImsFriendRequest imsFriendRequest) {
        Assert.notNull(imsFriendRequest,"add friend request can not be null");

        imsFriendRequest.setSendUserId(AuthUtils.ID());
        imsFriendRequest.setSendUserNickname(AuthUtils.NICKNAME());
        imsFriendRequest.setSendUserAvatar(AuthUtils.AVATAR());
        imsFriendRequest.setStatus(FriendRequestStateEnum.PendingPass.State());

        return this.save(imsFriendRequest);
    }

    @Override
    public List<ImsFriendRequest> getList(String userId) {
         return this.list(new QueryWrapper<ImsFriendRequest>().lambda().eq(ImsFriendRequest::getAcceptUserId,userId));
    }

    @Override
    public boolean updateFriendRequetsState(String requestId, Integer operationType) {
       return this.update(null, new UpdateWrapper<ImsFriendRequest>().eq("id", requestId).set("status", operationType));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean passFriendRequest(String requestId, String sendUserId, Integer operationType) {

        updateFriendRequetsState(requestId,operationType);

        saveFriends(sendUserId,AuthUtils.ID());
        saveFriends(AuthUtils.ID(),sendUserId);

        // todo 下推消息，通知请求发起者更新他的通讯录列表
        return false;
    }


    // 如果当前已经存在事务，那么加入该事务，如果不存在事务，创建一个事务，这是默认的传播属性值。
    @Transactional(propagation = Propagation.REQUIRED)
    private void saveFriends(String sendUserId, String acceptUserId) {
        ResultVo<UmsUsers> user = userServiceRPC.getUserByUniqueId(acceptUserId);

        ImsFriendRelation friendRelation = ImsFriendRelation.builder().userId(sendUserId).friendId(user.getData().getId())
                .friendNickname(user.getData().getNickname())
                .friendAvatar(user.getData().getAvatar()).firstLetter("x")
                .status(0).build();

        friendRelationService.save(friendRelation);
    }

}
