package burukeyou.im.api.controller;

import burukeyou.common.core.entity.vo.ResultVo;
import burukeyou.im.api.enity.dto.FriendRequestDto;
import burukeyou.im.api.enity.enums.FriendRequestStateEnum;
import burukeyou.im.api.enity.enums.IsCanSendFriendRequestEnum;
import burukeyou.im.api.service.FriendRequestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/friend/request")
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
        Integer result = friendRequestService.isCanSend(friendRequestDto.getAccept_user_id());

        // 发送好友请求
        if (result == IsCanSendFriendRequestEnum.SUCCESS.STATE){
            return friendRequestService.sendFriendRequest(friendRequestDto.converTo()) ? ResultVo.success():ResultVo.error("插入失败");
        }else {
            return ResultVo.error("添加失败");
        }
    }

    @GetMapping("/{userId}")
    @ApiOperation(value = "查看好友请求列表")
    public ResultVo getList(@PathVariable String userId){
        return ResultVo.success(friendRequestService.getList(userId));
    }

    // 通过/拒绝/忽略 好友请求
    @PostMapping("/operation")
    @ApiOperation(value = "操作好友请求")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "requestId"),
            @ApiImplicitParam(name = "sendUserId"),
            @ApiImplicitParam(name = "operationType")
    })
    public ResultVo operationFriendRequest(@NotBlank String requestId,@NotBlank String sendUserId, @NotNull Integer operationType){

        if (operationType == FriendRequestStateEnum.NOTPASS.State() || operationType == FriendRequestStateEnum.IGNORE.State()){
           return  ResultVo.success(friendRequestService.updateFriendRequetsState(requestId,operationType));
        }else if (operationType == FriendRequestStateEnum.PASS.State()){
            return ResultVo.success(friendRequestService.passFriendRequest(requestId,sendUserId,operationType));
        }else
            return ResultVo.error();

    }



}
