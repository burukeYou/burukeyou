package burukeyou.comment.controller;

import burukeyou.comment.entity.dto.ComentDto;
import burukeyou.comment.service.CommentService;
import burukeyou.common.core.entity.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.*;

/**
 *
 */
@Api("评论中心")
@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/publish")
    @ApiOperation(value = "发表评论")
    @ApiImplicitParam(name = "comentDto",value = "保存评论",required = true,dataType = "ComentDto")
    public ResultVo publishComment(@RequestBody ComentDto comentDto){
        try {
            commentService.save(comentDto.converTo());

            // todo 所属实体评论量加 1  (redis +  mq)

            // todo 消息通知


            return ResultVo.success();
        } catch (Exception e) {
            return ResultVo.error();
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除评论")
    @ApiImplicitParam(name = "id",value = "待删除评论ID",required = true,dataType = "String")
    public ResultVo deleteById(@PathVariable("id")String id){
        commentService.deleteByid(id);

        // todo 所属实体评论量减 1  (redis +  mq)

        return ResultVo.success();
    }


    @GetMapping("/{type}/{id}/ten")
    @ApiOperation(value = "获得某一个实体下点赞量最高的十个评论")
    public ResultVo getEntityTop10CommentList(@PathVariable("type") Integer type,@PathVariable("id")String id){
        return ResultVo.success(commentService.getTop10ThumbupList(type,id));
    }

    @GetMapping("/{type}/{id}")
    @ApiOperation(value = "分页获得某一个实体下所有最新评论")
    public ResultVo getEntityCommentList(@PathVariable("type") Integer type,@PathVariable("id")String id,
                                         @RequestParam("page") Integer page,@RequestParam("size")Integer size){
        return ResultVo.success(commentService.getLatestList(type,id,page,size));
    }






}
