package burukeyou.gateway.zuul.filter;

import burukeyou.auth.authClient.util.AuthUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *       2-审计过滤器
 */
public class ZuulAuditLogFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获得当前用户名 anonymousUser
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if ("anonymousUser".equals(username)){
            filterChain.doFilter(request, response);
            return;
        }

        System.out.println("1. add log for "+username); // anonymousUser

        // 放行
        filterChain.doFilter(request, response);

        // 失败时别的地方更新了日志，此处便不必再更新
        if(StringUtils.isBlank((String) request.getAttribute("isNeed"))) {
            System.out.println("3. update log to success");
        }
    }
}
