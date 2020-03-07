package burukeyou.file.entity.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;


@Data
@ConfigurationProperties(prefix = "custom.file")
//@RefreshScope
public class FileServerProperties {
    /**
     * 为以下3个值，指定不同的自动化配置
     * qiniu_oss：七牛oss
     * aliyun_oss：阿里云oss
     * fastdfs：本地部署的fastDFS
     */
    String type;

    /**
     * oss配置
     */
    OssProperties oss = new OssProperties();

    /**
     * fastDFS配置
     */
   // FdfsProperties fdfs = new FdfsProperties();
}
