package burukeyou.gateway.zuul.service.local;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface PermissionService {
    /**
     *      调用鉴权服务，判断用户authentication 是否有权限请求当前url
     * @param request 当前请求
     * @param authentication 用户信息
     * @return
     */
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
