package burukeyou.system.controller;

import burukeyou.common.core.entity.annotation.EnableParamValid;
import burukeyou.common.core.entity.vo.ResultVo;
import burukeyou.system.entity.dto.TopicDto;
import burukeyou.system.entity.pojo.SysTopic;
import burukeyou.system.rpc.FileServiceRPC;
import burukeyou.system.service.SysTopicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api("话题")
@RestController
@RequestMapping("/topic")
public class TopicController {

    private final SysTopicService sysTopicService;

    private final FileServiceRPC fileServiceRPC;


    public TopicController(SysTopicService sysTopicService, FileServiceRPC fileServiceRPC) {
        this.sysTopicService = sysTopicService;
        this.fileServiceRPC = fileServiceRPC;
    }

    @PostMapping
    @EnableParamValid
    @ApiOperation(value = "创建或者修改话题")
    public ResultVo add(TopicDto topicDto){
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
    public ResultVo getChannelPage(String name, int page, int size){
        return ResultVo.success(sysTopicService.getTopicPage(name,page,size));
    }


}
