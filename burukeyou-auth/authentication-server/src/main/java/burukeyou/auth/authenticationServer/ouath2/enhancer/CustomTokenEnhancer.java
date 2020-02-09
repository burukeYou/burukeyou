package burukeyou.auth.authenticationServer.ouath2.enhancer;

import burukeyou.auth.authenticationServer.entity.vo.UserTokenVo;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 *  增强颁发的token的携带信息
 *
 *  @author burukeyou
 *
 */
public class CustomTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        UserTokenVo user = (UserTokenVo) authentication.getPrincipal();

        final Map<String, Object> additionalInfo = new HashMap<>();

        additionalInfo.put("id", user.getId());
        additionalInfo.put("nickname", user.getNickname());
        additionalInfo.put("avatar", user.getAvatar());
        additionalInfo.put("description", user.getDescription());
        additionalInfo.put("blog_address", user.getBlog_address());

        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
        token.setAdditionalInformation(additionalInfo);

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);

        return accessToken;
    }
}
