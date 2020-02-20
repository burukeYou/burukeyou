package burukeyou.user.service.impl;

import burukeyou.auth.authClient.util.AuthUtils;
import burukeyou.user.entity.pojo.UmsFavorites;
import burukeyou.user.mapper.UmsFavoritesMapper;
import burukeyou.user.service.UmsFavoritesService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class UmsFavoritesServiceImpl extends ServiceImpl<UmsFavoritesMapper, UmsFavorites> implements UmsFavoritesService {


    @Override
    public boolean insertOrUpdate(UmsFavorites umsFavorites) {
        Assert.notNull(umsFavorites,"save favorites cant not be null");

        if (umsFavorites.getId() != null){
            UmsFavorites one = this.getOne(new QueryWrapper<UmsFavorites>().select("user_id").eq("id", umsFavorites.getId()));
            if (!one.getUserId().equals(AuthUtils.ID()))
                return false;
        }

        umsFavorites.setUserId(AuthUtils.ID());
        umsFavorites.setUserNickname(AuthUtils.NICKNAME());
        umsFavorites.setUserAvatar(AuthUtils.AVATAR());
        umsFavorites.setCount(0);
        return this.saveOrUpdate(umsFavorites);
    }

    @Override
    public boolean deleteById(String id) {
        // todo  1.以后写个DefaultService判断是否为超级管理员的权限，有则直接删除

        if (!IsEntityOwner(id))
            return false;

        // todo 2.mq异步化 将属于该收藏夹的东西解除与收藏夹的关系

        return this.deleteById(id);
    }

    @Override
    public UmsFavorites getById(String id) {
        return !IsEntityOwner(id) ? this.getOne(new QueryWrapper<UmsFavorites>().eq("id",id).eq("ispublic",true)):
                this.getById(id);
    }

    @Override
    public List<UmsFavorites> getListByUserId(String userId) {
        return (userId == null || !userId.equals(AuthUtils.ID()))?
                this.list(new QueryWrapper<UmsFavorites>().eq("user_id", userId).eq("ispublic", true))
                : this.list(new QueryWrapper<UmsFavorites>().eq("user_id", userId));
    }


    // todo 待重构
    private boolean IsEntityOwner(String entityId){
        UmsFavorites one = this.getOne(new QueryWrapper<UmsFavorites>().select("user_id").eq("id", entityId));
        return (one.getUserId()!= null && !one.getUserId().equals(AuthUtils.ID())) ? false : true;
    }


}
