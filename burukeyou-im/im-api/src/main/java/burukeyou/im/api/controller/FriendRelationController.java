package burukeyou.im.api.controller;

import burukeyou.auth.authClient.util.AuthUtils;
import burukeyou.common.core.entity.vo.ResultVo;
import burukeyou.im.api.enity.pojo.ImsFriendRelation;
import burukeyou.im.api.service.FriendRelationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/relation")
@RestController
public class FriendRelationController {

    private final FriendRelationService friendRelationService;

    public FriendRelationController(FriendRelationService friendRelationService) {
        this.friendRelationService = friendRelationService;
    }

    @GetMapping
    @ApiOperation(value = "获取用户好友列表")
    public ResultVo getFriendList(){
        List<ImsFriendRelation> list = friendRelationService.getFriendListByUserId(AuthUtils.ID());
        Map<String,List<ImsFriendRelation>> listMap = new HashMap<>();
        list.forEach(e ->{
            if (!listMap.containsKey(e.getFirstLetter())){
                List<ImsFriendRelation>  letterList = new ArrayList<>();
                listMap.put(e.getFirstLetter(),letterList);
            }
            List<ImsFriendRelation> tempList = listMap.get(e.getFirstLetter());
            tempList.add(e);
            listMap.put(e.getFirstLetter(),tempList);
        });
        return ResultVo.success(listMap);
    }


    @DeleteMapping("/{friendId}")
    @ApiOperation(value = "删除好友")
    public ResultVo deleteFriendRelation(@PathVariable("friendId") String friendId){
        // 解除好友关系
        friendRelationService.deleteFriendRelation(friendId);
        // todo 下推消息,重拉好友列表
        return ResultVo.success();
    }


}
