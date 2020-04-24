package burukeyou.like.controller;

import burukeyou.common.core.entity.vo.ResultVo;
import burukeyou.like.service.LikeService;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api("点赞")
@RequestMapping("/like")
@RestController
public class LikeController {


    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping("/{parentType}")
    @ApiOperation("批量判断当前用户是否关注该target")
    public ResultVo<Map<String,Boolean>> judgeIsLikeList(@PathVariable("parentType") String parentType, @RequestParam("parentIdList") List<String> parentIdList){
        return ResultVo.success(likeService.judgeIsLikeList(parentType,parentIdList));
    }


}
