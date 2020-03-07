package burukeyou.file.controller;

import burukeyou.common.core.entity.vo.ResultVo;
import burukeyou.file.service.FileService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
// todo  整合mq文件上传队列 保证高并发上传

@Api("文件资源管理")
@ResponseBody
@RequestMapping("/oss")
public class OssFileCntroller {

    private final FileService fileService;

    public OssFileCntroller(FileService fileService) {
        this.fileService = fileService;
    }

    @RequestMapping("/upload")
    public ResultVo uploadOne(@RequestParam("file") MultipartFile file){

        return null;
    }


    @DeleteMapping("/{id}")
    public ResultVo deleteOne(@PathVariable("id")String id){
        return null;
    }


}
