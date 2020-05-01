package burukeyou.im.api.service.impl;

import burukeyou.auth.authClient.util.AuthUtils;
import burukeyou.im.api.enity.pojo.ImsFriendRelation;
import burukeyou.im.api.mapper.FriendRelationMapper;
import burukeyou.im.api.service.FriendRelationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FriendRelationServiceImpl extends ServiceImpl<FriendRelationMapper, ImsFriendRelation> implements FriendRelationService {


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean deleteFriendRelation(String friendId) {
        return this.deleteFriendRelation(AuthUtils.ID(),friendId) && this.deleteFriendRelation(friendId,AuthUtils.ID());
    }

    @Override
    public List<ImsFriendRelation> getFriendListByUserId(String id) {
        return super.list(new QueryWrapper<ImsFriendRelation>().eq("user_id", id));
    }


    private boolean deleteFriendRelation(String userId, String friendId){
        return this.remove(new QueryWrapper<ImsFriendRelation>().lambda().eq(ImsFriendRelation::getUserId,userId)
                .eq(ImsFriendRelation::getFriendId,friendId));
    }



}
