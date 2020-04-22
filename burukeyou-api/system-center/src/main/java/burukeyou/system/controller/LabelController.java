package burukeyou.system.controller;

import burukeyou.auth.authClient.util.AuthUtils;
import burukeyou.common.core.entity.annotation.EnableParamValid;
import burukeyou.common.core.entity.vo.ResultVo;
import burukeyou.system.entity.dto.LabelDto;
import burukeyou.system.entity.dto.QueryLabelConditionDto;
import burukeyou.system.entity.dto.QueryTopicConditionDto;
import burukeyou.system.entity.enums.FocusStatusEnum;
import burukeyou.system.entity.enums.FocusTargetEnums;
import burukeyou.system.entity.pojo.SysLabel;
import burukeyou.system.entity.pojo.SysTopic;
import burukeyou.system.entity.vo.SysLabelVo;
import burukeyou.system.rpc.FileServiceRPC;
import burukeyou.system.rpc.FocusServiceRPC;
import burukeyou.system.service.SysLabelService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Api("标签")
@RestController
@RequestMapping("/label")
public class LabelController {

    private final SysLabelService sysLabelService;

    private final FileServiceRPC fileServiceRPC;

    private final FocusServiceRPC focusServiceRPC;

    public LabelController(SysLabelService sysLabelService, FileServiceRPC fileServiceRPC, FocusServiceRPC focusServiceRPC) {
        this.sysLabelService = sysLabelService;
        this.fileServiceRPC = fileServiceRPC;
        this.focusServiceRPC = focusServiceRPC;
    }

    @PostMapping
    @EnableParamValid
    @ApiOperation(value = "创建或者修改标签")
    public ResultVo add(@RequestBody LabelDto topicDto){
        MultipartFile avatar = topicDto.getTopicAvatar();
        SysLabel sysLabel = topicDto.converTo();
        if (avatar != null){
            ResultVo<String> resultVo = fileServiceRPC.uploadOne(avatar);
            sysLabel.setAvatar(resultVo.getData());
        }
        return ResultVo.success(sysLabelService.saveOrUpdateLabel(sysLabel));
    }



    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除标签")
    public ResultVo delete(@PathVariable("id") String id){
        return ResultVo.success(sysLabelService.deleteLabel(id));
    }

    @GetMapping("/page")
    @ApiOperation(value = "分页搜索标签")
    public ResultVo<Page<SysLabel>> getLabelPage(QueryLabelConditionDto conditionDto){
        return ResultVo.success(sysLabelService.getLabelPage(conditionDto));
    }

    @GetMapping("/app/page")
    public ResultVo<Page<SysLabelVo>> getAppLabelPage(QueryLabelConditionDto conditionDto){
        Page<SysLabel> sysLabelPage = sysLabelService.getLabelPage(conditionDto);
        List<String> targetIdList = new ArrayList<>();
        List<SysLabelVo> voList = sysLabelPage.getRecords().stream().map(e -> {
            targetIdList.add(e.getId());
            SysLabelVo sysLabelVo = new SysLabelVo().convertFrom(e);
            if (FocusStatusEnum.FOCUS.VALUE().equals(conditionDto.getFocusStatus()))
                sysLabelVo.setFollow(true);
            return sysLabelVo;
        }).collect(Collectors.toList());

        if (FocusStatusEnum.FOCUS.VALUE() .equals(conditionDto.getFocusStatus())){
            Page<SysLabelVo> page = new Page<>(sysLabelPage.getCurrent(), sysLabelPage.getSize(), sysLabelPage.getTotal());
            page.setRecords(voList);
            return ResultVo.success(page);
        }

        if (StringUtils.isNotBlank(AuthUtils.ID())){
            ResultVo<Map<String, Boolean>> labelMap = focusServiceRPC.judgeIsFollwerList(FocusTargetEnums.LABEL.VALUE(), targetIdList);
            if (labelMap != null)
                voList.forEach(e->e.setFollow(labelMap.getData().get(e.getId())));
        }

        Page<SysLabelVo> voPage = new Page<>(sysLabelPage.getCurrent(), sysLabelPage.getSize(), sysLabelPage.getTotal());
        voPage.setRecords(voList);

        return ResultVo.success(voPage);
    }

    @PutMapping("/{id}")
    public ResultVo updateFoucusCount(@PathVariable("id")String id,@RequestParam("count") int count){
        return ResultVo.success(sysLabelService.updateFoucusCount(id,count));
    }



}

