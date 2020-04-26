package burukeyou.file.controller;

import burukeyou.common.core.entity.vo.ResultVo;
import burukeyou.file.entity.dto.FileParamDto;
import burukeyou.file.entity.enums.FileDirTypeEnum;
import burukeyou.file.service.FileService;
import burukeyou.file.utils.Base64DecodeMultipartFile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;
// todo  整合mq文件上传队列 保证高并发上传

@Api("文件资源管理")
@RestController
@RequestMapping("/oss")
public class OssFileCntroller {

    private final FileService fileService;

    public OssFileCntroller(FileService fileService) {
        this.fileService = fileService;
    }


    @RequestMapping("/upload")
    @ApiOperation("上传单个文件")
    public ResultVo uploadOne(@RequestParam("file") MultipartFile file){

        return null;
    }

    @RequestMapping("/uploadList")
    @ApiOperation("批量上传多个上传多个文件")
    public ResultVo uploadList(List<MultipartFile> fileList){

        return null;
    }

    @RequestMapping(value = "/base64/uploadList",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("批量上传多个上传个base64")
    public ResultVo<List<String>> uploadListBase64(@RequestBody FileParamDto fileParamDto){
            if (!FileDirTypeEnum.isExits(fileParamDto.getType()))
            return  ResultVo.error("类型不对,阻止上传");

        List<MultipartFile> multipartFileList = fileParamDto.getFileList().stream()
                .map(Base64DecodeMultipartFile::base64Convert).collect(Collectors.toList());

        try {
            List<String> list = fileService.uploadFileList(fileParamDto.getType(), multipartFileList);
            return ResultVo.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.error("上传失败");
        }

    }


    @DeleteMapping("/{id}")
    public ResultVo deleteOne(@PathVariable("id")String id){
        return null;
    }


}
