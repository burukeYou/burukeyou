package burukeyou.article.controller;

import burukeyou.article.entity.bo.CountIncrementMsg;
import burukeyou.article.entity.dto.ArticleDto;
import burukeyou.article.entity.dto.ArticleQueryConditionDto;
import burukeyou.article.entity.enums.LikeParentTypeEnums;
import burukeyou.article.entity.pojo.AmsArticle;
import burukeyou.article.entity.vo.ArticleListlVo;
import burukeyou.article.mq.MqSender;
import burukeyou.article.rpc.LikeServiceRPC;
import burukeyou.article.service.ArticleService;
import burukeyou.common.core.entity.annotation.EnableParamValid;
import burukeyou.common.core.entity.vo.PageResultVo;
import burukeyou.common.core.entity.vo.ResultVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService  articleService;

    private final LikeServiceRPC likeServiceRPC;

    private final MqSender mqSender;

    public ArticleController(ArticleService articleService, LikeServiceRPC likeServiceRPC, MqSender mqSender) {
        this.articleService = articleService;
        this.likeServiceRPC = likeServiceRPC;
        this.mqSender = mqSender;
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
       // ArticleDetailVo articleDetailVo = new ArticleDetailVo().convertFrom(articleService.getById(id));
        //mqSender.send(id,"1");
        mqSender.send(new CountIncrementMsg(id,"1"));

        return ResultVo.success(articleService.getById(id));


       // return ResultVo.success(articleDetailVo);
    }


    @GetMapping("/page")
    @ApiOperation(value = "分页多条件获取文章列表",notes = "非拥有者只能查找公开的")
    public ResultVo getListByUserId(ArticleQueryConditionDto conditionDto){
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
