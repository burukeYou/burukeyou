package burukeyou.boiling.rpc;

import burukeyou.boiling.entity.dto.FileParamDto;
import burukeyou.boiling.rpc.fallbackFactory.FileServiceFallbackFactory;
import burukeyou.common.core.entity.bo.MicroServiceName;
import burukeyou.common.core.entity.vo.ResultVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = MicroServiceName.FILE_SERVER,fallbackFactory = FileServiceFallbackFactory.class)
public interface FileServiceRPC {

    @RequestMapping(value = "/oss/base64/uploadList",method = RequestMethod.POST)
    ResultVo<List<String>> uploadListBase64(@RequestBody FileParamDto fileParamDto);

}
