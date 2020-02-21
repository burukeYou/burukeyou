package burukeyou.article.controller;

import burukeyou.article.entity.bo.CountIncrementMsg;
import burukeyou.article.entity.dto.ArticleDto;
import burukeyou.article.entity.pojo.AmsArticle;
import burukeyou.article.entity.vo.ArticleListlVo;
import burukeyou.article.mq.MqSender;
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

import java.util.stream.Collectors;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    private MqSender mqSender;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    @EnableParamValid
    @ApiOperation(value = "保存或者修改文章")
    public ResultVo save(@RequestBody ArticleDto articleDto){

        MultipartFile backgroundFile = articleDto.getBackgroundFile();

        AmsArticle amsArticle = articleDto.converTo();

        return ResultVo.compute(articleService.insertOrUpdate(amsArticle));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据收藏夹id删除专栏,用户只能删除属于自己专收藏夹的id")
    @ApiImplicitParam(name = "id",value = "文章id",required = true,dataType = "String")
    public ResultVo delete(@PathVariable("id")  String id){
        return ResultVo.compute(articleService.deleteById(id));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获得文章",notes = "如果是查找别人的只能是公开的文章")
    @ApiImplicitParam(name = "id",value = "文章id",required = true,dataType = "String")
    public ResultVo getOne(@PathVariable("id") String id) {
       // ArticleDetailVo articleDetailVo = new ArticleDetailVo().convertFrom(articleService.getById(id));
        // todo  mq异步化 增加文章访问量
        //mqSender.send(id,"1");
        mqSender.send(new CountIncrementMsg(id,"1"));

        return ResultVo.success(articleService.getById(id));


       // return ResultVo.success(articleDetailVo);
    }


    @GetMapping("/list/{userId}")
    @ApiOperation(value = "分页根据用户id获取文章列表",notes = "非拥有者只能查找公开的")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id",required = true,dataType = "String"),
            @ApiImplicitParam(name = "page",value = "当前页",required = true,dataType = "long"),
            @ApiImplicitParam(name = "size",value = "每页显示大小",required = true,dataType = "long"),
    })
    public ResultVo getListByUserId(@PathVariable(value = "userId") String userId,
                                    @RequestParam("page")int page,
                                    @RequestParam("size")int size){

        Page<AmsArticle> list = articleService.getListByUserId(userId, page, size);

        PageResultVo<Object> build = PageResultVo.builder().page(list.getCurrent()).totalPage(list.getPages()).count(list.getTotal())
                .data(list.getRecords().stream().map(e -> new ArticleListlVo().convertFrom(e)).collect(Collectors.toList())).build();

        return ResultVo.success(build);
    }
}
