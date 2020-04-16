package burukeyou.like.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("点赞")
@RequestMapping("/")
@RestController
public class LikeController {


    @GetMapping
    @SentinelResource("hello")
    public String b(){
        return "BBQ";
    }

}
