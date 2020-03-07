package burukeyou.boiling.controller;

import burukeyou.boiling.entity.dto.BoilingDto;
import burukeyou.boiling.service.BoilingService;
import burukeyou.common.core.entity.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api("沸点")
@RestController
@RequestMapping("/boilling")
public class BoilingController {

    private final BoilingService boilingService;

    public BoilingController(BoilingService boilingService) {
        this.boilingService = boilingService;
    }

    @PostMapping
    @ApiOperation(value = "发布沸点")
    public ResultVo publishBoilling(@RequestBody BoilingDto boilingDto){
        return ResultVo.compute(boilingService.publish(boilingDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除沸点")
    public ResultVo deleteById(@PathVariable("id") String id){
        return ResultVo.compute(boilingService.deleteById(id));
    }


    @GetMapping("/{userId}")
    @ApiOperation(value = "分页获得某一用户所有公开沸点")
    public ResultVo getAllByUserId(@PathVariable("userId") String userId,  @RequestParam("page")int page,@RequestParam("size")int size){
        return ResultVo.success(boilingService.getListByUserId(userId,page,size));
    }

}
