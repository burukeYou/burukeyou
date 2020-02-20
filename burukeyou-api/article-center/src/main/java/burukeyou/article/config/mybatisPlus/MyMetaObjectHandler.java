package burukeyou.article.config.mybatisPlus;

import burukeyou.auth.authClient.util.AuthUtils;
import burukeyou.common.core.config.mybatisPlus.BsaePojoMetaObjectHandler;
import org.springframework.stereotype.Component;

/**
 *      mybatis plus 字段自动填充策略
 */
@Component
public class MyMetaObjectHandler extends BsaePojoMetaObjectHandler {
    @Override
    public String getCurrentUsername() {
        return AuthUtils.getCurrentUser() != null ? AuthUtils.getCurrentUser().getNickname() : "sysytem";
    }
}


