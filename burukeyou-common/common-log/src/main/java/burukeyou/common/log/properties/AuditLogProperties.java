package burukeyou.common.log.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "custom.audit-log")
//@RefreshScope //配置文件自动刷新
public class AuditLogProperties {

    /**
     *   whether open audit log features
     */
    private Boolean enabled = false;

    /**
     *   log location: such  es db , file
     */
    private String logLocation;
}
