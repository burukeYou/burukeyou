package burukeyou.comment.controller;

import burukeyou.auth.authClient.util.AuthUtils;
import burukeyou.comment.entity.dto.CommentDto;
import burukeyou.comment.entity.enums.CommentParentEnum;
import burukeyou.comment.entity.pojo.AmsComment;
import burukeyou.comment.service.CommentService;
import burukeyou.comment.service.MqService;
import burukeyou.common.core.entity.enums.NotificationTypeEnum;
import burukeyou.common.core.entity.vo.ResultVo;
import burukeyou.common.rabbitmq.entity.bo.NotificationContent;
import burukeyou.common.rabbitmq.entity.bo.NotificationDto;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

/**
 *         todo： （重构 => 与 mq的dao耦合
 */
@Api("评论中心")
@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    private final MqService mqService;

    public CommentController(CommentService commentService, MqService mqService) {
        this.commentService = commentService;
        this.mqService = mqService;
    }

    @PostMapping("/publish")
    @ApiOperation(value = "发表评论")
    @ApiImplicitParam(name = "comentDto",value = "保存评论",required = true,dataType = "ComentDto")
    public ResultVo publishComment(@RequestBody CommentDto commentDto){
        try {
            commentService.save(commentDto.converTo());

            // todo 所属实体评论量加 1  (redis +  mq)

            // 异步下发消息通知
            //buildNotiffication(commentDto);

            return ResultVo.success();
        } catch (Exception e) {
            return ResultVo.error();
        }
    }

    @Async
    void buildNotiffication(CommentDto commentDto){
        NotificationContent content = NotificationContent.builder()
                .userId(AuthUtils.ID()).nickname(AuthUtils.NICKNAME()).avatar(AuthUtils.AVATAR())
                .tt("评论了你的").pd(CommentParentEnum.getTypeName(commentDto.getParentType()))
                .title(commentDto.getParentTitle()).build();
        mqService.publishNotification(new NotificationDto(JSON.toJSONString(content),commentDto.getParentUserId(), NotificationTypeEnum.USER.VALUE()));
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
    public ResultVo getEntityTop10CommentList(@PathVariable("type") String type,@PathVariable("id")String id){
        return ResultVo.success(commentService.getTop10ThumbupList(type,id));
    }

    @GetMapping("/{type}/{id}")
    @ApiOperation(value = "分页获得某一个实体下所有最新评论")
    public ResultVo getPageNewly(@PathVariable("type") String type,@PathVariable("id")String id,
                                         @RequestParam("page") Integer page,@RequestParam("size")Integer size){
        return ResultVo.success(commentService.getLatestList(type,id,page,size));
    }

    @GetMapping("/{id}")
    public ResultVo<AmsComment> getDetail(@PathVariable("id") String id){
        return ResultVo.success(commentService.getById(id));
    }




}
