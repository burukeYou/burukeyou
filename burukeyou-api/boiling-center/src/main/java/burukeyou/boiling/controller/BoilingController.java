package burukeyou.boiling.controller;

import burukeyou.boiling.entity.dto.BoilingDto;
import burukeyou.boiling.entity.dto.QueryConditionDto;
import burukeyou.boiling.entity.pojo.AmsBoiling;
import burukeyou.boiling.entity.vo.AmsBoilingVo;
import burukeyou.boiling.rpc.FocusServiceRPC;
import burukeyou.boiling.rpc.LikeServiceRPC;
import burukeyou.boiling.service.BoilingService;
import burukeyou.common.core.entity.vo.PageResultVo;
import burukeyou.common.core.entity.vo.ResultVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Api("沸点")
@RestController
@RequestMapping("/boiling")
public class BoilingController {

    private final BoilingService boilingService;

    private final FocusServiceRPC focusServiceRPC;

    private final LikeServiceRPC likeServiceRPC;

    public BoilingController(BoilingService boilingService, FocusServiceRPC focusServiceRPC, LikeServiceRPC likeServiceRPC) {
        this.boilingService = boilingService;
        this.focusServiceRPC = focusServiceRPC;
        this.likeServiceRPC = likeServiceRPC;
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


    @GetMapping("/page")
    @ApiOperation(value = "条件分页获得某一用户沸点")
    public ResultVo getPageCondition(QueryConditionDto dto){
        Page<AmsBoiling> pageRes = boilingService.getPageCondition(dto);

        List<String> userIdList = new ArrayList<>();
        List<String> parentIdList = new ArrayList<>();
        pageRes.getRecords().forEach(e->{
            userIdList.add(e.getUserId());
            parentIdList.add(e.getId());
        });

        ResultVo<Map<String, Boolean>> focusMap = focusServiceRPC.judgeIsFollwerList("USER", userIdList);
        ResultVo<Map<String, Boolean>> likeMap = likeServiceRPC.judgeIsLikeList("BOILING", parentIdList);

        List<AmsBoilingVo> voList = pageRes.getRecords().stream().map(e -> {
            AmsBoilingVo vo = new AmsBoilingVo().convertFrom(e);
            Optional.ofNullable(e.getContentPic()).ifPresent(pic -> vo.setContentPic(pic.split("\\s*\\$\\$\\s*")));

            if (focusMap != null && focusMap.getData() != null)
                vo.setFollow(focusMap.getData().get(e.getUserId()));

            if (likeMap != null && likeMap.getData() != null)
                vo.setThumbup(likeMap.getData().get(e.getId()));
            return vo;
        }).collect(Collectors.toList());


        Page<AmsBoilingVo> res = new Page<>(pageRes.getCurrent(), pageRes.getSize(), pageRes.getTotal());
        res.setRecords(voList);
        //PageResultVo<AmsBoilingVo> data = PageResultVo.<AmsBoilingVo>builder().page(pageRes.getCurrent()).totalPage(pageRes.getPages()).count(pageRes.getTotal()).data(list).build();
        return ResultVo.success(res);
    }

}
