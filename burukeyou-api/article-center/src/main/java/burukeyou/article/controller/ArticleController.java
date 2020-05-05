package burukeyou.article.controller;

import burukeyou.article.entity.dto.ArticleDto;
import burukeyou.article.entity.dto.ArticleQueryConditionDto;
import burukeyou.article.entity.pojo.AmsArticle;
import burukeyou.article.entity.vo.ArticleDetailVo;
import burukeyou.article.entity.vo.ArticleListlVo;
import burukeyou.article.service.impl.RabbitmqServiceImpl;
import burukeyou.article.rpc.FocusServiceRPC;
import burukeyou.article.rpc.LikeServiceRPC;
import burukeyou.article.rpc.UserServiceRPC;
import burukeyou.article.service.ArticleService;
import burukeyou.common.core.entity.annotation.EnableParamValid;
import burukeyou.common.core.entity.enums.CollectionsTypeEnum;
import burukeyou.common.core.entity.enums.LikeParentTypeEnums;
import burukeyou.common.core.entity.vo.ResultVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService  articleService;

    private final LikeServiceRPC likeServiceRPC;

    private final RabbitmqServiceImpl mqSender;

    private final FocusServiceRPC focusServiceRPC;

    private final UserServiceRPC userServiceRPC;

    public ArticleController(ArticleService articleService, LikeServiceRPC likeServiceRPC, RabbitmqServiceImpl mqSender, FocusServiceRPC focusServiceRPC, UserServiceRPC userServiceRPC) {
        this.articleService = articleService;
        this.likeServiceRPC = likeServiceRPC;
        this.mqSender = mqSender;
        this.focusServiceRPC = focusServiceRPC;
        this.userServiceRPC = userServiceRPC;
    }

    @PostMapping
    @EnableParamValid
    @ApiOperation(value = "保存或者修改文章")
    public ResultVo save(@RequestBody ArticleDto articleDto){
        return ResultVo.compute(articleService.publishArticle(articleDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据id删除,用户只能删除属于自己的")
    @ApiImplicitParam(name = "id",value = "文章id",required = true,dataType = "String")
    public ResultVo delete(@PathVariable("id")  String id){
        return ResultVo.compute(articleService.deleteById(id));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获得文章",notes = "如果是查找别人的只能是公开的文章")
    @ApiImplicitParam(name = "id",value = "文章id",required = true,dataType = "String")
    public ResultVo getOne(@PathVariable("id") String id) {
        AmsArticle article = articleService.getById(id);
        ArticleDetailVo vo = new ArticleDetailVo().convertFrom(articleService.getById(id));
        vo.setLabels(Arrays.asList( article.getLabels().split("\\s*\\$\\$\\s*")));

        //
        ResultVo<Map<String, Boolean>> rv = likeServiceRPC.judgeIsLikeList(LikeParentTypeEnums.ARTICLE.VALUE(), Arrays.asList(vo.getId()));
        if (rv != null && rv.getData() != null) {
           vo.setLike(rv.getData().get(vo.getId()));
        }
        //
        ResultVo<Map<String, Boolean>> focusVO = focusServiceRPC.judgeIsFollwerList("USER", Arrays.asList(vo.getUserId()));
        if (focusVO != null && focusVO.getData() != null)
            vo.setFocusUser(focusVO.getData().get(vo.getUserId()));
        //
        String favoritiesId = userServiceRPC.judgeIsFavorities(CollectionsTypeEnum.Article.getType(), vo.getId());
        if (favoritiesId != null){
            vo.setFavoritiesId(favoritiesId);
            vo.setFavorities(true);
        }

        mqSender.incrVisitCount(id);
        return ResultVo.success(vo);
    }


    @GetMapping("/page")
    @ApiOperation(value = "分页多条件获取文章列表",notes = "非拥有者只能查找公开的")
    public ResultVo<Page<ArticleListlVo> > getListByUserId(ArticleQueryConditionDto conditionDto){
        Page<AmsArticle> page = articleService.getListByUserId(conditionDto);

        List<String> targteIds = new ArrayList<>();
        List<ArticleListlVo> voList = page.getRecords().stream().map(e -> {
            ArticleListlVo vo = new ArticleListlVo().convertFrom(e);
            targteIds.add(e.getId());

            return vo;
        }).collect(Collectors.toList());

        ResultVo<Map<String, Boolean>> rv = likeServiceRPC.judgeIsLikeList(LikeParentTypeEnums.ARTICLE.VALUE(), targteIds);
        if (rv != null && rv.getData() != null) {
            voList.forEach(e -> e.setThumbup(rv.getData().get(e.getId())));
        }

        Page<ArticleListlVo> resultPage = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        resultPage.setRecords(voList);
        return ResultVo.success(resultPage);
    }
}
