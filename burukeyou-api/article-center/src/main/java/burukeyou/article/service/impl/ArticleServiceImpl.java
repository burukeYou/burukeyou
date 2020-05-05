package burukeyou.article.service.impl;

import burukeyou.article.entity.bo.VisitCount;
import burukeyou.article.entity.dto.ArticleDto;
import burukeyou.article.entity.dto.ArticleQueryConditionDto;
import burukeyou.article.entity.dto.LabelDto;
import burukeyou.article.entity.enums.StateEnum;
import burukeyou.article.entity.pojo.AmsArticle;
import burukeyou.article.mapper.ArticleMapper;
import burukeyou.article.service.ArticleService;
import burukeyou.article.service.MqService;
import burukeyou.auth.authClient.util.AuthUtils;
import burukeyou.common.rabbitmq.entity.bo.ArticleLabel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, AmsArticle> implements ArticleService {

    @Autowired
    private MqService mqService;

    @Override
    public boolean publishArticle(ArticleDto articleDto) {
        AmsArticle amsArticle = articleDto.converTo();

        MultipartFile backgroundFile = articleDto.getBackgroundFile();
        if (backgroundFile != null){

        }

        boolean isEdit = true;
        if (StringUtils.isBlank(articleDto.getId())){
            amsArticle.setId(IdWorker.getIdStr());
            isEdit = false;
        }

        List<String> labelIds = new ArrayList<>();
        List<LabelDto> labels = articleDto.getLabels();
        if (labels != null && labels.size() > 0){
            StringBuilder sb = new StringBuilder();
            labels.forEach(e -> {
                sb.append(e.getName()).append("$$");
                labelIds.add(e.getId());
            });
            amsArticle.setLabels(sb.toString());
            // todo 该标签文章量加一
        }

        mqService.buildArticleWithLabelRelations(new ArticleLabel(amsArticle.getId(), labelIds));

        //
        return this.insertOrUpdate(amsArticle,isEdit);
    }

    @Override
    public boolean insertOrUpdate(AmsArticle amsArticle,boolean isEdit) {
        Assert.notNull(amsArticle,"article to create or update cant not be null");

        // only the owner of umsColumn can update it
        if (isEdit){
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
            amsArticle.setThumbupCount(0);
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
    public AmsArticle getById(String id) {
        return IsEntityOwner(id) ? super.getById(id) : super.getOne(new QueryWrapper<AmsArticle>().lambda().eq(AmsArticle::getId,id).eq(AmsArticle::getIspublic,true));
        //ArticleDetailVo oneDetailById = baseMapper.getOneDetailById(id);
        //return oneDetailById ;
    }

    @Override
    public Page<AmsArticle> getListByUserId(ArticleQueryConditionDto conditionDto) {
        Page<AmsArticle> page = new Page<>(conditionDto.getPage(),conditionDto.getSize());
        conditionDto.setLoginUserId(AuthUtils.ID());
        return baseMapper.getPageCondition(page,conditionDto);
    }

    @Override
    public void updateVisitCountBatch(List<VisitCount> list) {
        if(CollectionUtils.isEmpty(list))
            return;
        list.forEach(e-> baseMapper.updateVisitCount(e));
    }

    // todo 待重构
    private boolean IsEntityOwner(String entityId){
        AmsArticle one = this.getOne(new QueryWrapper<AmsArticle>().select("user_id").eq("id", entityId));
        return (one.getUserId()!= null && !one.getUserId().equals(AuthUtils.ID())) ? false : true;
    }

}
