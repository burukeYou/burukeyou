package burukeyou.system.controller;

import burukeyou.common.core.entity.annotation.EnableParamValid;
import burukeyou.common.core.entity.vo.ResultVo;
import burukeyou.system.entity.dto.ChannelDto;
import burukeyou.system.entity.vo.ChannelVo;
import burukeyou.system.service.SysChannelService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


/**
 *     todo need auth
 */
@Api("频道")
@RestController
@RequestMapping("/channel")
public class ChannelController {

    private final SysChannelService sysChannelService;

    public ChannelController(SysChannelService sysChannelService) {
        this.sysChannelService = sysChannelService;
    }

    @PostMapping
    @EnableParamValid
    @ApiOperation(value = "创建修改频道")
    public ResultVo add(ChannelDto channelDto){
        return ResultVo.success(sysChannelService.saveOrUpdate(channelDto.converTo()));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除频道")
    public ResultVo delete(@PathVariable("id") String id){
        return ResultVo.success(sysChannelService.deleteChannel(id));
    }

    @GetMapping("/page")
    public ResultVo getChannelPage(String name, int page, int size){
        return ResultVo.success(sysChannelService.getChannelPage(name,page,size));
    }




}
