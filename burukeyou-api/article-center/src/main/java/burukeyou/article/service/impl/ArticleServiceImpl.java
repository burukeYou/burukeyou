package burukeyou.article.service.impl;

import burukeyou.article.entity.enums.StateEnum;
import burukeyou.article.entity.pojo.AmsArticle;
import burukeyou.article.entity.vo.ArticleDetailVo;
import burukeyou.article.mapper.ArticleMapper;
import burukeyou.article.service.ArticleService;
import burukeyou.auth.authClient.util.AuthUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, AmsArticle> implements ArticleService {


    @Override
    public boolean insertOrUpdate(AmsArticle amsArticle) {
        Assert.notNull(amsArticle,"article to create or update cant not be null");

        // only the owner of umsColumn can update it
        if (amsArticle.getId() != null){
            if (!IsEntityOwner(amsArticle.getId()))
                return false;
        }else {
            if (StringUtils.isBlank(amsArticle.getImage()))
                amsArticle.setImage("/aaa/bb");

            amsArticle.setUserId(AuthUtils.ID());
            amsArticle.setUserNickname(AuthUtils.NICKNAME());
            amsArticle.setUserAvatar(AuthUtils.AVATAR());
            amsArticle.setVisitsCount(0);
            amsArticle.setCommentCount(0);
            amsArticle.setCommentCount(0);
            amsArticle.setState(StateEnum.PendingReview.STATE());
        }

        return this.saveOrUpdate(amsArticle);
    }

    @Override
    public boolean deleteById(String id) {
        //1
        if (!IsEntityOwner(id))
            return false;

        //2 todo 删除该文章下保存的图片

        //3
        return this.removeById(id);
    }

    @Override
    public ArticleDetailVo getById(String id) {
        ArticleDetailVo oneDetailById = baseMapper.getOneDetailById(id);

        //
      /*  AmsArticle amsArticle;
        if (!IsEntityOwner(id)){
            amsArticle = this.getOne(new QueryWrapper<AmsArticle>().eq("id",id).eq("ispublic",true)
                                                            .eq("state", StateEnum.PASS));
        }else {
            amsArticle = this.getOne(new QueryWrapper<AmsArticle>().eq("id",id).and(e->e.eq("state", StateEnum.PASS)
                        .or().eq("state",StateEnum.NOTPASS)));
        }
*/
        //
        return oneDetailById ;
    }

    @Override
    public Page<AmsArticle> getListByUserId(String userId,long currentPage,long size) {
        //      不返回总记录数 设置false
        Page<AmsArticle> page = new Page<>(currentPage,size);

        Page<AmsArticle> amsArticlePage;
        if (!userId.equals(AuthUtils.ID())){
          amsArticlePage = this.page(page, new QueryWrapper<AmsArticle>().eq("user_id", userId)
                    .eq("ispublic", true).eq("state", StateEnum.PASS.STATE()));

        }else {
            amsArticlePage = this.page(page, new QueryWrapper<AmsArticle>().eq("user_id",userId)
                    .and(e->e.eq("state", StateEnum.PASS.STATE()).or().eq("state",StateEnum.NOTPASS.STATE())));
        }

        return amsArticlePage;
    }

    // todo 待重构
    private boolean IsEntityOwner(String entityId){
        AmsArticle one = this.getOne(new QueryWrapper<AmsArticle>().select("user_id").eq("id", entityId));
        return (one.getUserId()!= null && !one.getUserId().equals(AuthUtils.ID())) ? false : true;
    }

}
