package burukeyou.file.config;

import burukeyou.file.entity.properties.FileServerProperties;
import burukeyou.file.service.impl.AliyunOssServiceImpl;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *      阿里云OSS自动配置
 */
@Configuration
@ConditionalOnProperty(name = "custom.file.type", havingValue = "aliyun_oss")
public class AliyunOSSAutoConfigure {

    private final FileServerProperties fileProperties;

    public AliyunOSSAutoConfigure(FileServerProperties fileProperties) {
        this.fileProperties = fileProperties;
    }


    @Bean
    public OSSClient ossClient() {
        return (OSSClient) new OSSClientBuilder().build(fileProperties.getOss().getEND_POINT(), fileProperties.getOss().getACCESS_KEY_ID(),
                fileProperties.getOss().getACCESS_KEY_SECRET());
    }

    @Bean
    public AliyunOssServiceImpl aliyunOssService(){
        return new AliyunOssServiceImpl(ossClient(),fileProperties.getOss());
    }


}
