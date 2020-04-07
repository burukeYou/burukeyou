package burukeyou.boiling.controller;

import burukeyou.boiling.entity.dto.BoilingDto;
import burukeyou.boiling.entity.pojo.AmsBoiling;
import burukeyou.boiling.entity.vo.AmsBoilingVo;
import burukeyou.boiling.service.BoilingService;
import burukeyou.common.core.entity.vo.PageResultVo;
import burukeyou.common.core.entity.vo.ResultVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Page<AmsBoiling> pageRes = boilingService.getListByUserId(userId, page, size);
        List<AmsBoilingVo> list =pageRes.getRecords().stream().map(e -> {
            AmsBoilingVo vo = new AmsBoilingVo().convertFrom(e);
            Optional.ofNullable(e.getContentPic()).ifPresent(pic-> vo.setContentPic(pic.split("\\s*\\$\\$\\s*")));
            vo.setFollow(true);    // todo 调用关注微服务判断是否关注或者点赞
            vo.setThumbup(false);  // todo 调用点赞微服务判断是否关注或者点赞
            return vo;
        }).collect(Collectors.toList());

        PageResultVo<AmsBoilingVo> data = PageResultVo.<AmsBoilingVo>builder().page(pageRes.getCurrent()).totalPage(pageRes.getPages()).count(pageRes.getTotal()).data(list).build();
        return ResultVo.success(data);
    }

}
