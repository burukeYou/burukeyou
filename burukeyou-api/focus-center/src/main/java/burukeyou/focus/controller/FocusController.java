package burukeyou.focus.controller;

import burukeyou.common.core.entity.vo.ResultVo;
import burukeyou.focus.service.UmsFocusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResultVo add(@RequestParam("targetId") String targetId,@RequestParam("targetType") String targetType){
        focusService.focus(targetId,targetType);

        return null;
    }

}



