package burukeyou.common.redis.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "custom.redis")
public class CustomRedisProperties {

    public boolean isOpen = false;


}
