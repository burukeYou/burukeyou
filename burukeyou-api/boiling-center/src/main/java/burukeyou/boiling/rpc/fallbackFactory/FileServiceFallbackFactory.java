package burukeyou.boiling.rpc.fallbackFactory;

import burukeyou.boiling.entity.dto.FileParamDto;
import burukeyou.boiling.rpc.FileServiceRPC;
import burukeyou.common.core.entity.vo.ResultVo;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Slf4j
@Component
public class FileServiceFallbackFactory implements FallbackFactory<FileServiceRPC> {
    @Override
    public FileServiceRPC create(Throwable throwable) {
        return new FileServiceRPC(){
            @Override
            public ResultVo uploadListBase64(@RequestBody FileParamDto fileParamDto) {
                log.error("调用文件服务失败降级!!",throwable);
                return null;
            }
        };
    }
}
