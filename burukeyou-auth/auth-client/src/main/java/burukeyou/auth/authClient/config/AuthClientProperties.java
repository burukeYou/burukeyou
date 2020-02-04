package burukeyou.auth.authClient.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "custom.ouath2.client")
public class AuthClientProperties {

    private String signingKey;

    private String resourceId;

    private List<String> ignoreUrls;
}
