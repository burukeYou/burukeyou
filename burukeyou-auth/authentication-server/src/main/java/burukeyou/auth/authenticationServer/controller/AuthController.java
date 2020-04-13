package burukeyou.auth.authenticationServer.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;


@Slf4j
@Api(tags = "认证相关")
@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Resource
    private AuthorizationServerTokenServices authorizationServerTokenServices;


    @Autowired
    private ClientDetailsService clientDetailsService;


    @ApiOperation(value = "账号密码认证")
    @PostMapping("account/login")
    public void getUserTokenInfo(
            @ApiParam(required = true, name = "username", value = "账号") String username,
            @ApiParam(required = true, name = "password", value = "密码") String password,
            HttpServletRequest request, HttpServletResponse response){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
       buidlToken(request, response, token, "用户名或密码错误");
    }

    //       xhr.setRequestHeader('Authorization', 'Basic ' + window.btoa(config.clientId + ":" + config.clientSecret));
    // 构建认证的token 交给认证管理器认证
    private void buidlToken(HttpServletRequest request, HttpServletResponse response, AbstractAuthenticationToken token, String errorMsg) {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Basic"))
            throw new UnapprovedClientAuthenticationException("请求头中client信息为空");

        // 从请求中解析出客户端id和客户端密钥 clientId clientSecret
        String clientId = "";
        String clientSecret ="";
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);

        if (clientDetails == null) {
            throw new UnapprovedClientAuthenticationException("clientId对应的信息不存在");
        } else if (!passwordEncoder.matches(clientSecret, clientDetails.getClientSecret())) {
            throw new UnapprovedClientAuthenticationException("clientSecret不匹配");
        }



        TokenRequest tokenRequest = new TokenRequest(new HashMap<>(1), clientId, clientDetails.getScope(), "customer");
        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);


        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);


        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);

        // 生成token
        OAuth2AccessToken oAuth2AccessToken = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
        oAuth2Authentication.setAuthenticated(true);

        // 把oAuth2AccessToken客户端返回即可
    }


}
