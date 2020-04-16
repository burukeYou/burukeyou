package burukeyou.system.rpc.fallBack;

import burukeyou.common.core.entity.vo.ResultVo;
import burukeyou.system.rpc.FileServiceRPC;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileServiceFallBack implements FileServiceRPC {
    @Override
    public ResultVo<String> uploadOne(MultipartFile file) {
        return ResultVo.error("上传失败");
    }

    @Override
    public ResultVo deleteOne(String id) {
        return ResultVo.error("删除失败");
    }
}
