package burukeyou.im.api.controller;

import burukeyou.auth.authClient.util.AuthUtils;
import burukeyou.common.core.entity.vo.ResultVo;
import burukeyou.im.api.enity.pojo.ImsFriendRelation;
import burukeyou.im.api.service.FriendRelationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/friend")
@RestController
public class FriendRelationController {

    private final FriendRelationService friendRelationService;

    public FriendRelationController(FriendRelationService friendRelationService) {
        this.friendRelationService = friendRelationService;
    }

    @GetMapping
    @ApiOperation(value = "获取用户好友列表")
    public ResultVo getFriendList(){
        List<ImsFriendRelation> list = friendRelationService.list(new QueryWrapper<ImsFriendRelation>().eq("user_id", AuthUtils.ID()));
        return ResultVo.success(list);
    }


    @DeleteMapping("/{friendId}")
    @ApiOperation(value = "解除好友关系")
    public ResultVo deleteFriendRelation(@PathVariable("friendId") String friendId){
        // 解除好友关系
        friendRelationService.deleteFriendRelation(friendId);

        // todo 下推消息通知该friendId用户，他被当前用户删除

        return ResultVo.success();
    }

    // 搜索好友

}
