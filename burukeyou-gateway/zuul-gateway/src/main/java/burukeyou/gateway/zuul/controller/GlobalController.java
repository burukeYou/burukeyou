package burukeyou.gateway.zuul.controller;

import burukeyou.auth.authClient.entity.CurrentUserInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GlobalController {

    @GetMapping("/a")
    @PreAuthorize("isAuthenticated()")
    public String get(@AuthenticationPrincipal CurrentUserInfo userInfo){
        return "1";
    }

    @GetMapping("/b")
    public String get02(@AuthenticationPrincipal CurrentUserInfo userInfo){
        return "2";
    }

    @GetMapping("/c")
    @PreAuthorize("hasRole('SBBSBS')")
    public String get03(@AuthenticationPrincipal CurrentUserInfo userInfo){
        return "3";
    }

}
