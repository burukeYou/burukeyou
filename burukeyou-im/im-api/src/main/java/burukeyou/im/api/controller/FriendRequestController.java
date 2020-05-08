package burukeyou.im.api.controller;

import burukeyou.common.core.entity.vo.ResultVo;
import burukeyou.im.api.enity.dto.FriendRequestDto;
import burukeyou.im.api.enity.enums.FriendRequestStateEnum;
import burukeyou.im.api.enity.enums.IsCanSendFriendRequestEnum;
import burukeyou.im.api.enity.pojo.ImsFriendRequest;
import burukeyou.im.api.service.FriendRequestService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/request")
@Api("朋友关系api")
public class FriendRequestController {

    private final FriendRequestService friendRequestService;

    public FriendRequestController(FriendRequestService friendRequestService) {
        this.friendRequestService = friendRequestService;
    }

    // todo 幂等性
    @PostMapping
    @ApiOperation(value = "发送添加好友请求")
    public ResultVo addFriendRequest(@Valid @RequestBody FriendRequestDto friendRequestDto){
        //判断是否能发送好友请求
        Integer result = friendRequestService.isCanSend(friendRequestDto.getAcceptUserId());

        // 发送好友请求
        if (result.equals(IsCanSendFriendRequestEnum.SUCCESS.STATE)){
            return friendRequestService.sendFriendRequest(friendRequestDto.converTo()) ? ResultVo.success():ResultVo.error("发送失败");
        }else {
            return ResultVo.error(IsCanSendFriendRequestEnum.Msg(result));
        }
    }

    @GetMapping("/{userId}")
    @ApiOperation(value = "查看好友请求列表")
    public ResultVo<List<ImsFriendRequest>> getFriendRequestList(@PathVariable String userId){
        return ResultVo.success(friendRequestService.getList(userId));
    }

    // 通过/拒绝/忽略 好友请求
    @PostMapping("/operation")
    @ApiOperation(value = "操作好友请求")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sendUserId"),
            @ApiImplicitParam(name = "operation")
    })
    public ResultVo operationFriendRequest(String sendUserAvatar,String sendUserNickname,@NotBlank String sendUserId, @NotNull Integer operation){
        if (!FriendRequestStateEnum.isExist(operation))
            return  ResultVo.error("该操作类型不允许");

        if (operation.equals(FriendRequestStateEnum.NOTPASS.State()) || operation.equals(FriendRequestStateEnum.IGNORE.State())){
           return  ResultVo.success(friendRequestService.updateFriendRequetsState(sendUserId,operation));
        }else if (operation.equals(FriendRequestStateEnum.PASS.State())){
            return ResultVo.success(friendRequestService.passFriendRequest(sendUserId,sendUserNickname,sendUserAvatar,operation));
        }else
            return ResultVo.error("操作失败");

    }

    @GetMapping("/count")
    public ResultVo getFrequestCount(){
        int count = friendRequestService.count(new QueryWrapper<ImsFriendRequest>().lambda().eq(ImsFriendRequest::getStatus, FriendRequestStateEnum.PendingPass.State()));
        return ResultVo.success(count);
    }


}
