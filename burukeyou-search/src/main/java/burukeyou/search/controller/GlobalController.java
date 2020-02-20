package burukeyou.search.controller;

import burukeyou.auth.authClient.entity.CurrentUserInfo;
import burukeyou.auth.authClient.util.AuthUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
@Api(value = "全局搜索接口",description = "...")
public class GlobalController {

    @GetMapping("/a")
    @PreAuthorize("isAuthenticated()")
    public String get(@AuthenticationPrincipal CurrentUserInfo userInfo){
        return "1";
    }

    @GetMapping("/b")
    @ApiOperation(value = "查找2",notes = "xx")
    public String get02(@AuthenticationPrincipal CurrentUserInfo userInfo){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "2";
    }

    @GetMapping("/c")
    public String get03(@AuthenticationPrincipal CurrentUserInfo userInfo){
        CurrentUserInfo currentUser = AuthUtils.getCurrentUser();

        System.out.println(userInfo);
        System.out.println(currentUser);

        return "3";
    }

}
