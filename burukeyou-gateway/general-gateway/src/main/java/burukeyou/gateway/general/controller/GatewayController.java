package burukeyou.gateway.general.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class GatewayController {


    @GetMapping
    public String get(){
        return "2";
    }

}
