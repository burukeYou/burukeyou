package burukeyou.auth.authClient.config.oauth2;

import burukeyou.auth.authClient.entity.CurrentUserInfo;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *  jwt 转化用户信息
 * @author burukeyou
 *
 */
public class CustomUserAuthenticationConverter implements UserAuthenticationConverter {

    private static final String N_A = "N/A";

    @Override
    public Authentication extractAuthentication(Map<String, ?> map) {
        if (!map.containsKey(USERNAME))
            return null;

        CurrentUserInfo user = new CurrentUserInfo();
        user.setId((String) map.get("id"));
        user.setUsername((String) map.get(USERNAME));
        user.setNickname((String) map.get("nickname"));
        user.setAvatar((String) map.get("avatar"));

        if (map.containsKey("authorities") && map.get("authorities") != null){
            Collection<? extends GrantedAuthority> authorities = getAuthorities(map);
            user.setAuthorities(authorities);
            return new UsernamePasswordAuthenticationToken(user, N_A,authorities);
        }else {
            return new UsernamePasswordAuthenticationToken(user, N_A,null);
        }
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Map<String, ?> map) {
        Object authorities = map.get(AUTHORITIES);
        if (authorities instanceof String) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList((String) authorities);
        }
        if (authorities instanceof Collection) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils
                    .collectionToCommaDelimitedString((Collection<?>) authorities));
        }else if (authorities == null){

        }
        throw new IllegalArgumentException("Authorities must be either a String or a Collection");
    }


    // 复制 DefaultUserAuthenticationConverter
    @Override
    public Map<String, ?> convertUserAuthentication(Authentication authentication) {
        Map<String, Object> response = new LinkedHashMap<String, Object>();
        response.put(USERNAME, authentication.getName());
        if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
            response.put(AUTHORITIES, AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
        }
        return response;
    }
}
