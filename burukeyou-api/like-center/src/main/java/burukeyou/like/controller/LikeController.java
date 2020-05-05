package burukeyou.like.controller;

import burukeyou.common.core.entity.vo.ResultVo;
import burukeyou.like.service.LikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
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
    @ApiOperation("批量判断当前用户是否点赞target")
    public ResultVo<Map<String,Boolean>> judgeIsLikeList(@PathVariable("parentType") String parentType, @RequestParam("parentIdList") List<String> parentIdList){
        return ResultVo.success(likeService.judgeIsLikeList(parentType,parentIdList));
    }

    @PostMapping("/{isLike}/{parentType}/{parentId}")
    @ApiOperation("点赞/取消点赞")
    public ResultVo postLike(@NotBlank @PathVariable("parentType") String parentType,
                             @NotBlank @PathVariable("parentId") String parentId,
                             @NotBlank @PathVariable("isLike") boolean isLike){
        likeService.postLike(parentId,parentType,isLike);
        return ResultVo.success();
    }

    // 为保证有序性共用一个接口,否则两个接口可能先发了 cancel 然后发 post，但是post先到了

   /* @DeleteMapping("/{parentType}/{parentId}")
    @ApiOperation("取消点赞")
    public ResultVo cancelLike(@NotBlank @PathVariable("parentType") String parentType,
                             @NotBlank @PathVariable("parentId") String parentId){

        likeService.cancelLike(parentId,parentType);
        return ResultVo.success();
    }*/



}
