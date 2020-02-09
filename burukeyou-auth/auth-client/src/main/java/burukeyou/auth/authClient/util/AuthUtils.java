package burukeyou.auth.authClient.util;

import burukeyou.auth.authClient.entity.CurrentUserInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtils {

    private AuthUtils(){}

    /**
     *  get current login user info
     * @return
     */
    public static CurrentUserInfo getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return  authentication != null ? (CurrentUserInfo)authentication.getPrincipal():null;
    }
}
