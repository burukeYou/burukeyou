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
import burukeyou.im.api.utils.PinYInUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
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

        if (count > 0)
            return IsCanSendFriendRequestEnum.ALREADY_FRIENDS.STATE;


        return IsCanSendFriendRequestEnum.SUCCESS.STATE;
    }

    @Override
    public boolean sendFriendRequest(ImsFriendRequest imsFriendRequest) {
        Assert.notNull(imsFriendRequest,"add friend request can not be null");

        imsFriendRequest.setSendUserId(AuthUtils.ID());
        imsFriendRequest.setSendUserNickname(AuthUtils.NICKNAME());
        imsFriendRequest.setSendUserAvatar(AuthUtils.AVATAR());
        imsFriendRequest.setStatus(FriendRequestStateEnum.PendingPass.State());
        imsFriendRequest.setCreateTime(LocalDateTime.now());
        return this.save(imsFriendRequest);
    }

    @Override
    public List<ImsFriendRequest> getList(String userId) {
         return this.list(new QueryWrapper<ImsFriendRequest>().lambda().eq(ImsFriendRequest::getAcceptUserId,userId));
    }

    @Override
    public boolean updateFriendRequetsState(String sendUserId, Integer operationType) {
        return super.update(new UpdateWrapper<ImsFriendRequest>().lambda()
                .set(ImsFriendRequest::getStatus,operationType)
                .eq(ImsFriendRequest::getSendUserId,sendUserId)
                .eq(ImsFriendRequest::getAcceptUserId,AuthUtils.ID()));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public boolean passFriendRequest(String sendUserId, String sendUserNickname,String sendUserAvatar,Integer operationType) {

        updateFriendRequetsState(sendUserId,operationType);

        saveFriends(AuthUtils.ID(),sendUserId, sendUserNickname, sendUserAvatar);
        saveFriends(sendUserId,AuthUtils.ID(),AuthUtils.NICKNAME(),AuthUtils.AVATAR());

        // todo 下推消息，通知请求发起者更新他的通讯录列表
        return false;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveFriends(String userId, String friendId,String friendNickname,String friendAvatar) {
        ImsFriendRelation friendRelation = ImsFriendRelation.builder()
                .userId(userId)
                .friendId(friendId)
                .friendNickname(friendNickname)
                .friendAvatar(friendAvatar).firstLetter(PinYInUtils.getFirstLetter(friendNickname))
                .status(0).createTime(LocalDateTime.now()).build();
        // todo 下发通知
        friendRelationService.save(friendRelation);
    }

}
