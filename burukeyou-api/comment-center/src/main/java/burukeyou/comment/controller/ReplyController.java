package burukeyou.comment.controller;

import burukeyou.comment.entity.dto.ReplyDto;
import burukeyou.comment.entity.pojo.AmsReply;
import burukeyou.comment.service.ReplyService;
import burukeyou.common.core.entity.vo.ResultVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reply")

public class ReplyController {

    private final ReplyService replyService;

    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @PostMapping("/publish")
    @ApiOperation(value = "发表回复")
    public ResultVo publishComment(@RequestBody ReplyDto commentDto){
        return ResultVo.compute(replyService.publicReply(commentDto.converTo()));
    }

    @GetMapping("/{commentId}")
    @ApiOperation("获得评论下回复")
    public ResultVo<Page<AmsReply>> getReplyByCommentId(@PathVariable("commentId") String commentId,int page,int size){
        return ResultVo.success(replyService.getReplyByCommentId(commentId,page,size));
    }

}
