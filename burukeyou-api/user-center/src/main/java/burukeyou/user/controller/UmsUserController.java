package burukeyou.user.controller;

import burukeyou.common.core.entity.annotation.EnableParamValid;
import burukeyou.common.core.entity.vo.ResultVo;
import burukeyou.user.entity.dto.LoginDto;
import burukeyou.user.entity.dto.UserDto;
import burukeyou.user.entity.pojo.UmsUser;
import burukeyou.user.entity.vo.TokenInfo;
import burukeyou.user.service.UmsUserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

/** 普通用户注册登陆管理
 *
 * @author burukeyou
 */
@RestController
@RequestMapping("/user")
@Api("user")
@Slf4j
public class UmsUserController {

    private final RestTemplate restTemplate;
    private final UmsUserService umsUserService;

    public UmsUserController(RestTemplate restTemplate, UmsUserService umsUserService) {
        this.restTemplate = restTemplate;
        this.umsUserService = umsUserService;
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登陆", notes = "。。")
    @ApiImplicitParam(name = "loginDto", value = "登陆的账号和密码参数", required = true, dataType = "LoginDto")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = ResultVo.class))
    public ResultVo login(@Valid @RequestBody LoginDto loginDto){
        log.info("{} is try to login",loginDto.getUsername());

        // 去认证中心获取token
        String oauthServiceUrl = "http://authentication-center-server/oauth/token"; //

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
        params.add("type","user");

        // 设置请求体
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

        ResponseEntity<TokenInfo> response = null;
        try {
            response = restTemplate.exchange(oauthServiceUrl, HttpMethod.POST, entity, TokenInfo.class);
        } catch (RestClientException e) {
            log.error("{}  login fail",loginDto.getUsername());
            return ResultVo.error("账号或者密码错误");
        }

        TokenInfo body = response.getBody();
        return ResultVo.success( body.init());
    }


    @PostMapping("/save")
    @EnableParamValid
    @ApiOperation(value = "用户注册或者修改用户信息")
    @ApiImplicitParam(name = "userDto", value = "用户信息", required = true, dataType = "UserDto")
    public ResultVo registerOrUpdate(@RequestBody UserDto userDto){
        return ResultVo.compute(umsUserService.saveOrupdate(userDto.converTo()))  ;
    }


    @GetMapping("/uniqueId")
    @ApiOperation(value = "根据用户账号或者手机号或者邮箱查找用户")
    @ApiImplicitParam(paramType = "query",name = "uniqueId",value = "用户账号或者手机号或者邮箱",required = true,dataType = "string")
    public ResultVo<UmsUser> getUserByUniqueId(@RequestParam String uniqueId){
        log.debug("query with username or mobile or email:{}", uniqueId);
        return ResultVo.success(umsUserService.getByUniqueId(uniqueId)) ;
    }
}
