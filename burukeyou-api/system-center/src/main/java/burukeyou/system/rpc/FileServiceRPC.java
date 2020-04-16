package burukeyou.system.rpc;

import burukeyou.common.core.entity.bo.MicroServiceName;
import burukeyou.common.core.entity.vo.ResultVo;
import burukeyou.system.rpc.fallBack.FileServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *  fallback:       分别回退处理
 *  fallbackFactory： 统一回退处理，还可以获得导致回退处理的原因,打日志，打异常
 *
 */
@FeignClient(name = MicroServiceName.FILE_SERVER,fallback = FileServiceFallBack.class)
public interface FileServiceRPC {

    @RequestMapping("/upload")
    ResultVo<String> uploadOne(@RequestParam("file") MultipartFile file);


    @DeleteMapping("/{id}")
    ResultVo deleteOne(@PathVariable("id") String id);

}
