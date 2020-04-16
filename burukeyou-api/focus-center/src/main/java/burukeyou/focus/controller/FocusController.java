package burukeyou.focus.controller;

import burukeyou.common.core.entity.vo.ResultVo;
import burukeyou.focus.service.UmsFocusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api("关注服务")
@RestController
@RequestMapping("/")
public class FocusController {

    private UmsFocusService focusService;

    public FocusController(UmsFocusService focusService) {
        this.focusService = focusService;
    }

    @PostMapping
    @ApiOperation(value = "关注")
    public ResultVo postFocus(@RequestParam("targetId") String targetId,@RequestParam("targetType") String targetType){
        return ResultVo.compute(focusService.focus(targetId,targetType));
    }

    @DeleteMapping("/{type}/{id}")
    @ApiOperation("取消关注")
    public ResultVo cancelFocus(@PathVariable("type") String type,@PathVariable String id){
        return ResultVo.compute(focusService.cancelFocus(type,id));
    }

    @GetMapping
    public String a(){
        return "SBSBBSB";
    }
}



