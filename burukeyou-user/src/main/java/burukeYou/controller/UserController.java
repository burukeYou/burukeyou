package burukeYou.controller;

import burukeYou.entity.bo.TokenInfo;
import burukeYou.entity.dto.LoginDto;
import burukeYou.entity.pojo.UmsUser;
import burukeYou.entity.vo.UserTokenVo;
import burukeYou.service.UserService;
import common.entity.vo.ResultVo;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@RestController
@Api("user")
@RequestMapping("/user")
public class UserController {

    private final RestTemplate restTemplate;
    private final UserService userService;

    public UserController(RestTemplate restTemplate,UserService userService) {
        this.restTemplate = restTemplate;
        this.userService = userService;
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登陆", notes = "。。")
    @ApiImplicitParam(name = "loginDto", value = "登陆的账号和密码参数", required = true, dataType = "LoginDto")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = ResultVo.class))
    public ResultVo login(@Valid @RequestBody LoginDto loginDto, HttpServletRequest request){
        log.info("{} is try to login",loginDto.getUsername());

        // 去认证中心获取token
       // String oauthServiceUrl = "http://localhost:9070/token/oauth/token";
        String oauthServiceUrl = "http://authentication-server/oauth/token"; //

        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // 设置basic 认证
        headers.setBasicAuth("burukeyou-user", "123456");

        // 设置请求参数
        // 不能能hashMap，  MultiValueMap是一个key对应多个值
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("username", loginDto.getUsername());
        params.add("password", loginDto.getPassword());
        params.add("grant_type", "password");
        params.add("scope", "get add");

        // 设置请求体
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

        ResponseEntity<TokenInfo> response = null;
        try {
            response = restTemplate.exchange(oauthServiceUrl, HttpMethod.POST, entity, TokenInfo.class);
        } catch (RestClientException e) {
            log.info("{}  login fail",loginDto.getUsername());
            return ResultVo.error("账号或者密码错误");
        }

        TokenInfo body = response.getBody();

        return ResultVo.success(body);
    }


    @GetMapping
    @ApiOperation(value = "根据用户账号或者手机号查找用户")
    @ApiImplicitParam(paramType = "query",name = "uniqueId",value = "用户账号或者手机号",required = true,dataType = "string")
    public ResultVo getUserByUserName(@RequestParam String uniqueId,@AuthenticationPrincipal UserTokenVo umsUser){
        log.debug("query with username or mobile:{}", uniqueId);
        return ResultVo.success(userService.getByUniqueId(uniqueId));
    }
}
