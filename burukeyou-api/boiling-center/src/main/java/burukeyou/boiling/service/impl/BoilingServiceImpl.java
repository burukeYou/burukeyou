package burukeyou.boiling.service.impl;

import burukeyou.auth.authClient.util.AuthUtils;
import burukeyou.boiling.entity.dto.BoilingDto;
import burukeyou.boiling.entity.enums.StateEnum;
import burukeyou.boiling.entity.pojo.AmsBoiling;
import burukeyou.boiling.mapper.BoillingMapper;
import burukeyou.boiling.service.BoilingService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class BoilingServiceImpl extends ServiceImpl<BoillingMapper,AmsBoiling> implements BoilingService {

    @Override
    public boolean publish(BoilingDto boilingDto) {
        // todo   调用file-server异步上传
        List<MultipartFile> imgfiles = boilingDto.getImgfiles();

        AmsBoiling amsBoiling = boilingDto.converTo();
        amsBoiling.setUserId(AuthUtils.ID());
        amsBoiling.setUserNickname(AuthUtils.USERNAME());
        amsBoiling.setUserAvatar(AuthUtils.AVATAR());
        amsBoiling.setCommentCount(0);
        amsBoiling.setThumbupCount(0);
        amsBoiling.setVisitsCount(0);
        amsBoiling.setState(StateEnum.PASS.STATE());
        amsBoiling.setUrl("xx");

        return this.save(amsBoiling);
    }

    @Override
    public Page<AmsBoiling> getListByUserId(String userId, int currentPage, int size) {
        Page<AmsBoiling> page = new Page<>(currentPage,size);

        Page<AmsBoiling> amsBoilingPage;
        if (!userId.equals(AuthUtils.ID())){
            amsBoilingPage = this.page(page, new QueryWrapper<AmsBoiling>().eq("user_id", userId)
                    .eq("ispublic", true).eq("state", StateEnum.PASS.STATE()));

        }else {
            amsBoilingPage = this.page(page, new QueryWrapper<AmsBoiling>().eq("user_id",userId)
                    .and(e->e.eq("state", StateEnum.PASS.STATE()).or().eq("state",StateEnum.NOTPASS.STATE())));
        }
        return amsBoilingPage;
    }


    @Override
    public boolean deleteById(String id) {
        //1
        if (!IsEntityOwner(id))
            return false;

        //2 todo 删除该boiling下保存的图片

        //3
        return this.removeById(id);
    }





    // todo 待重构
    private boolean IsEntityOwner(String entityId){
        AmsBoiling one = this.getOne(new QueryWrapper<AmsBoiling>().select("user_id").eq("id", entityId));
        return (one != null && one.getUserId() != null && !one.getUserId().equals(AuthUtils.ID())) ? false : true;
    }
}
