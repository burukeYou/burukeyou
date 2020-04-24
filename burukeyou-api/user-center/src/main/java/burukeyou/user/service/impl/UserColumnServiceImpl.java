package burukeyou.user.service.impl;

import burukeyou.auth.authClient.util.AuthUtils;
import burukeyou.user.entity.enums.StateEnum;
import burukeyou.user.entity.pojo.UmsColumn;
import burukeyou.user.mapper.UmsColumnMapper;
import burukeyou.user.service.UserColumnService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

//        queryWrapper.select(User.class, info -> !info.getColumn().equals("manager_id")
@Service
public class UserColumnServiceImpl extends ServiceImpl<UmsColumnMapper, UmsColumn> implements UserColumnService {

    @Override
    public boolean insertOrUpdate(UmsColumn umsColumn) {
        Assert.notNull(umsColumn,"umsColumn to create or update cant not be null");

        // only the owner of umsColumn can update it
        if (umsColumn.getId() != null){
            UmsColumn one = this.getOne(new QueryWrapper<UmsColumn>().select("user_id").eq("id", umsColumn.getId()));
            if (!one.getUserId().equals(AuthUtils.ID()))
                return false;
        }else {
            if (StringUtils.isBlank(umsColumn.getImage()))
                umsColumn.setImage("/xxx/xx");

            umsColumn.setIstop(false);
            umsColumn.setState(StateEnum.PendingReview.getState());
            umsColumn.setUserId(AuthUtils.ID());
        }
        return this.saveOrUpdate(umsColumn);
    }

    @Override
    public boolean deleteById(String id) {
        // only user of umsColumn can delete umsColumn
        if (!IsEntityOwner(id))
            return false;

        // todo rabbit异步化执行，把属于该专栏的文章的专类类型置为默认专栏
        return this.deleteById(id);
    }

    @Override
    public UmsColumn getById(String id) {
        return  (!IsEntityOwner(id)) ? this.getOne(new QueryWrapper<UmsColumn>().eq("id",id).eq("ispublic",true))
                :super.getById(id);
    }

    @Override
    public List<UmsColumn> getListByUserId(String userId) {
        return (userId == null || !userId.equals(AuthUtils.ID()))?
                this.list(new QueryWrapper<UmsColumn>().eq("user_id", userId).eq("ispublic", true))
                : this.list(new QueryWrapper<UmsColumn>().eq("user_id", userId));
    }

    @Override
    public Page<UmsColumn> getPageByUserId(String userId, int page, int size) {
        Page<UmsColumn> of = new Page<>(page, size);
        Page<UmsColumn> umsColumnPage = (userId == null || !userId.equals(AuthUtils.ID())) ?
                this.page(of, new QueryWrapper<UmsColumn>().eq("user_id", userId).eq("ispublic", true))
                : this.page(of, new QueryWrapper<UmsColumn>().eq("user_id", userId));

        return umsColumnPage;
    }

    private boolean IsEntityOwner(String entityId){
        UmsColumn one = this.getOne(new QueryWrapper<UmsColumn>().select("user_id").eq("id", entityId));
        return (one.getUserId()!= null && !one.getUserId().equals(AuthUtils.ID())) ? false : true;
    }


}
