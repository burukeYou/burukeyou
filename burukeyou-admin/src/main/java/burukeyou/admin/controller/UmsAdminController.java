package burukeyou.admin.controller;

import burukeyou.admin.entity.bo.TokenInfo;
import burukeyou.admin.entity.dto.LoginDto;
import burukeyou.admin.entity.dto.QueryUserConditionDto;
import burukeyou.admin.entity.dto.UmsAdminDto;
import burukeyou.admin.entity.vo.UmsAdminVO;
import burukeyou.admin.rpc.FileServiceRPC;
import burukeyou.admin.service.UmsAdminService;
import burukeyou.common.core.entity.vo.ResultVo;
import burukeyou.common.log.annotation.AuditLog;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Set;

@Slf4j
@RestController
@Api("用户管理")
@RequestMapping("/admin")
public class UmsAdminController {

    private final RestTemplate restTemplate;

    private final UmsAdminService umsAdminService;

    private FileServiceRPC fileServiceRPC;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UmsAdminController(RestTemplate restTemplate, UmsAdminService umsAdminService, FileServiceRPC fileServiceRPC) {
        this.restTemplate = restTemplate;
        this.umsAdminService = umsAdminService;
        this.fileServiceRPC = fileServiceRPC;
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登陆", notes = "。。")
    @ApiImplicitParam(name = "loginDto", value = "登陆的账号和密码参数", required = true, dataType = "LoginDto")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = ResultVo.class))
    public ResultVo login(@Valid @RequestBody LoginDto loginDto, HttpServletRequest request){
        log.info("{} is try to login",loginDto.getUsername());

        System.out.println(passwordEncoder.encode("123456"));

        // 去认证中心获取token
       // String oauthServiceUrl = "http://localhost:9070/token/oauth/token";
        String oauthServiceUrl = "http://authentication-center-server/oauth/token"; //

        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // 设置basic 认证
        headers.setBasicAuth("burukeyou-admin", "123456");

        // 设置请求参数
        // 不能能hashMap，  MultiValueMap是一个key对应多个值
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("username", loginDto.getUsername());
        params.add("password", loginDto.getPassword());
        params.add("grant_type", "password");
        params.add("scope", "get add");
        params.add("type","admin");

        // 设置请求体
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

        ResponseEntity<TokenInfo> response = null;
        try {
            response = restTemplate.exchange(oauthServiceUrl, HttpMethod.POST, entity, TokenInfo.class);
        } catch (RestClientException e) {
            log.info("{}  login fail",loginDto.getUsername());
            return ResultVo.error("账号或者密码错误");
        }

        response.getBody();
        TokenInfo body = response.getBody();

        return ResultVo.success(body);
    }


  /*  @GetMapping("/uniqueId")
    @ApiOperation(value = "根据用户账号或者手机号查找用户")
    @ApiImplicitParam(paramType = "query",name = "uniqueId",value = "用户账号或者手机号",required = true,dataType = "string")
    public ResultVo getUserByUserName(@RequestParam String uniqueId){
        log.debug("query with username or mobile:{}", uniqueId);
        return ResultVo.success(umsAdminService.getByUniqueId(uniqueId));
    }*/

    //@AuditLog(logInfo = "'新增用户:'+ #umsAdminDto.adminName")
    @PostMapping
   // @EnableParamValid
    @ApiOperation("新增或者修改后台用户信息")
    @ApiImplicitParam(name = "userDto", value = "用户信息", required = true, dataType = "UserDto")
    public ResultVo addOrUpdate(@RequestBody UmsAdminDto umsAdminDto){

        // todo 上传文件
        MultipartFile avatarFile = umsAdminDto.getAvatarFile();
        if (avatarFile != null){
            String data = fileServiceRPC.uploadOne(avatarFile).getData();
            umsAdminDto.setAvatar(data);
        }
        return ResultVo.compute(umsAdminService.saveOrupdate(umsAdminDto));
    }


    @DeleteMapping("{id}")
    @ApiOperation(value = "删除用户")
    @ApiImplicitParam(name = "id",value = "用户id",dataType = "String")
    public ResultVo deleteById(@PathVariable("id") String id){
        return ResultVo.compute(umsAdminService.deleteById(id));
    }

    @GetMapping
    @ApiOperation("多条件分页获取用户列表")
    @ApiImplicitParam(name = "查找用户的筛选条件")
    public ResultVo list(QueryUserConditionDto queryUserConditionDto){
        Page<UmsAdminVO> umsAdminPage =  umsAdminService.getListByCondition(queryUserConditionDto);
        return ResultVo.success(umsAdminPage);
    }

    @PostMapping("{userId}/roles")
    @ApiOperation("给用户分配角色")
    public ResultVo updateUserRole(@PathVariable String userId, @RequestBody Set<String> roleIdList){
        return ResultVo.compute(umsAdminService.setRoleOfUser(userId,roleIdList));
    }


    @GetMapping("/{userId}")
    @ApiOperation("根据ID获取管理员信息")
    public ResultVo getOneById(@PathVariable("userId") String id){
        return ResultVo.success(umsAdminService.getOneById(id));
    }

    //todo 导入用户


    //todo 导出用户












}
