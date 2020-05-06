package burukeyou.search.server.controller;


import burukeyou.search.server.temp.SysLabel;
import burukeyou.search.server.temp.SysLableServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/search")
@Api(value = "全局搜索接口")
public class GlobalController {

//    @GetMapping("/a")
//    @PreAuthorize("isAuthenticated()")
//    public String gloableSearch(){
//        return "1";
//    }
    




}
