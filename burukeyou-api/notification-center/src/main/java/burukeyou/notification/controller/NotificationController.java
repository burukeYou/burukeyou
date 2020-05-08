package burukeyou.notification.controller;

import burukeyou.common.core.entity.vo.ResultVo;
import burukeyou.notification.entity.vo.NotificationVo;
import burukeyou.notification.service.NotificationService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.web.bind.annotation.*;

@Api("通知")
@RestController
@RequestMapping("/notification")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/publish")
    @ApiOperation("发布通知")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "acceptId",value = "消息接收者id",required = true,dataType = "String"),
            @ApiImplicitParam(name = "content", value = "消息内容", required = true, dataType = "String"),
            @ApiImplicitParam(name = "type", value = "消息类型", required = true, dataType = "String")
    })
    public ResultVo publishNotification(String acceptId, String content,String type){
        return notificationService.publishNotification(acceptId,content,type) ? ResultVo.success() : ResultVo.error("发送失败");
    }

    @GetMapping("/page")
    @ApiOperation("根据通知类型获取通知")
    public ResultVo<Page<NotificationVo>> getNotificationPage(String type, int page, int size){
        return ResultVo.success(notificationService.getNotificationPage(type,page,size));
    }

    @PostMapping("/read/{id}")
    @ApiOperation(value = "阅读通知")
    public ResultVo readNotification(@PathVariable("id") String id){
       return ResultVo.compute(notificationService.readNotification(id)) ;
    }



}
