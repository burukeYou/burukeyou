package burukeyou.auth.authClient.util;

import burukeyou.auth.authClient.entity.CurrentUserInfo;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author burukeyou
 *
 */
public class AuthUtils {

    private AuthUtils(){}

    /**
     *  get current login user info
     * @return
     */
    public static CurrentUserInfo getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return  (authentication instanceof AnonymousAuthenticationToken || authentication == null) ? null: (CurrentUserInfo)authentication.getPrincipal();
    }

    /**
     *
     * @return current login user unique id
     */
    public static String ID(){
        CurrentUserInfo currentUser = getCurrentUser();
        return currentUser == null ? null : currentUser.getId();
    }

    /**
     * @return current login user username
     */
    public static String USERNAME(){
        CurrentUserInfo currentUser = getCurrentUser();
        return currentUser == null ? null : currentUser.getUsername();
    }


    /**
     * @return current login user nickname
     */
    public static String NICKNAME(){
        CurrentUserInfo currentUser = getCurrentUser();
        return currentUser == null ? null : currentUser.getNickname();
    }


    /**
     * @return current login user avatar
     */
    public static String AVATAR(){
        CurrentUserInfo currentUser = getCurrentUser();
        return currentUser == null ? null : currentUser.getAvatar();
    }



}
