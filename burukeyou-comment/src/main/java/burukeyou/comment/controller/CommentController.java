package burukeyou.comment.controller;

import burukeyou.auth.authClient.entity.CurrentUserInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    @GetMapping("/b")
    public String get02(@AuthenticationPrincipal CurrentUserInfo userInfo){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "2";
    }



}
