package burukeyou.system.controller;

import burukeyou.auth.authClient.util.AuthUtils;
import burukeyou.common.core.entity.annotation.EnableParamValid;
import burukeyou.common.core.entity.vo.ResultVo;
import burukeyou.system.entity.dto.QueryTopicConditionDto;
import burukeyou.system.entity.dto.TopicDto;
import burukeyou.system.entity.pojo.SysTopic;
import burukeyou.system.entity.vo.TopicVo;
import burukeyou.system.rpc.FileServiceRPC;
import burukeyou.system.rpc.FocusServiceRPC;
import burukeyou.system.service.SysTopicService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Api("话题")
@RestController
@RequestMapping("/topic")
public class TopicController {

    private final SysTopicService sysTopicService;

    private final FileServiceRPC fileServiceRPC;

    private final FocusServiceRPC focusServiceRPC;

    public TopicController(SysTopicService sysTopicService, FileServiceRPC fileServiceRPC, FocusServiceRPC focusServiceRPC) {
        this.sysTopicService = sysTopicService;
        this.fileServiceRPC = fileServiceRPC;
        this.focusServiceRPC = focusServiceRPC;
    }

    @PostMapping
    @EnableParamValid
    @ApiOperation(value = "创建或者修改话题")
    public ResultVo add(@RequestBody TopicDto topicDto){
        MultipartFile avatar = topicDto.getTopicAvatar();
        SysTopic topic = topicDto.converTo();
        if (avatar != null){
            ResultVo<String> resultVo = fileServiceRPC.uploadOne(avatar);
            topic.setAvatar(resultVo.getData());
        }
        return ResultVo.success(sysTopicService.saveOrUpdateTopic(topic));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除话题")
    public ResultVo delete(@PathVariable("id") String id){
        return ResultVo.success(sysTopicService.deleteTopic(id));
    }

    @GetMapping("/page")
    @ApiOperation(value = "分页搜索话题")
    public ResultVo<Page<SysTopic>> getChannelPage(QueryTopicConditionDto conditionDto){
        return ResultVo.success(sysTopicService.getTopicPage(conditionDto));
    }

    @GetMapping("/app/page")
    @ApiOperation(value = "分页搜索话题")
    public ResultVo<Page<TopicVo>> getChannelPage(int page ,int size){
        Page<SysTopic> topicPage = sysTopicService.page(new Page<>(page, size));

        List<String> targetIdList = new ArrayList<>();
        List<TopicVo> voList = topicPage.getRecords().stream().map(e -> {
            targetIdList.add(e.getId());
            TopicVo topicVo = new TopicVo().convertFrom(e);
            return topicVo;
        }).collect(Collectors.toList());

        if (StringUtils.isNotBlank(AuthUtils.ID())){
            ResultVo<Map<String, Boolean>> topicMap = focusServiceRPC.judgeIsFollwerList("TOPIC", targetIdList);
            voList.forEach(e -> e.setFollow(topicMap.getData().get(e)));
        }

        Page<TopicVo> result = new Page<>(topicPage.getCurrent(), topicPage.getSize(), topicPage.getTotal());
        result.setRecords(voList);
        return ResultVo.success(result);
    }


}
