package burukeyou.admin.rpc.fallback;

import burukeyou.admin.rpc.FileServiceRPC;
import burukeyou.common.core.entity.vo.ResultVo;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
@Component
public class FileServiceRPCFallbackFactory implements FallbackFactory<FileServiceRPC> {
    @Override
    public FileServiceRPC create(Throwable throwable) {
        return new FileServiceRPC(){
            @Override
            public ResultVo uploadOne(MultipartFile file) {
                log.error("调用文件服务上传文件异常：{}", file.getName(), throwable);


                return null;
            }

            @Override
            public ResultVo deleteOne(String id) {
                return null;
            }


        };
    }
}
