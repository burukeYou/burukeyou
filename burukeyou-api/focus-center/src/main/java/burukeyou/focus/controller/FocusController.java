package burukeyou.focus.controller;

import burukeyou.common.core.entity.vo.ResultVo;
import burukeyou.focus.service.UmsFocusService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

@Api("关注服务")
@RestController
@RequestMapping("/focus")
public class FocusController {

    private UmsFocusService focusService;

    public FocusController(UmsFocusService focusService) {
        this.focusService = focusService;
    }

    @PostMapping
    @ApiOperation(value = "关注")
    public ResultVo postFocus(@NotBlank String targetId,@NotBlank String targetType){
        return ResultVo.compute(focusService.focus(targetType,targetId));
    }

    @DeleteMapping("/{type}/{id}")
    @ApiOperation("取消关注")
    public ResultVo cancelFocus(@PathVariable("type") String type,@PathVariable("id") String id){
        return ResultVo.compute(focusService.cancelFocus(type,id));
    }

    @GetMapping("/{targetType}")
    @ApiOperation("批量判断当前用户是否关注该target")
    //@PreAuthorize("isAuthenticated()") //SecurityExpressionRoot
    public ResultVo<Map<String,Boolean>> judgeIsFollwerList(@PathVariable("targetType") String targetType, @RequestParam("targetidList") List<String> targetidList){
        return ResultVo.success(focusService.judgeIsFollwerList(targetType,targetidList));
    }

    @GetMapping(value = "/{userId}/{targetType}/page")
    @ApiOperation("获取用户在targetType下关注的所有target")
    public ResultVo<Page<String>> getUserFocusTargetPage(@PathVariable("userId") String userId,
                                                         @PathVariable("targetType") String targetType,
                                                         @RequestParam("page") int page,@RequestParam("size")  int size){
        return ResultVo.success(focusService.getUserFocusTargetPage(userId,targetType,page,size));
    }



}



