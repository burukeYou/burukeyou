package burukeyou.gateway.zuul.service.local.impl;

import burukeyou.gateway.zuul.service.local.PermissionService;
import burukeyou.gateway.zuul.service.rpc.AuthorizationServerRPC;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired(required = false)
    private AuthorizationServerRPC authorizationServerRPC;

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        // todo rpc调用鉴权服务
        System.out.println("rpc调用鉴权服务");
        return false;
    }
}
