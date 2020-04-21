package burukeyou.auth.authenticationServer.ouath2;

import burukeyou.auth.authenticationServer.entity.vo.UserTokenVo;
import burukeyou.auth.authenticationServer.dao.UmsUserMapper;
import burukeyou.auth.authenticationServer.entity.pojo.UmsUser;
import burukeyou.auth.authenticationServer.service.local.UserService;
import burukeyou.auth.authenticationServer.service.rpc.UserServiceRPC;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class Ouath2UserDetailService extends ServiceImpl<UmsUserMapper, UmsUser> implements UserDetailsService {

    private final UserService userService;

    @Autowired
    private UserServiceRPC userServiceRPC;

    public Ouath2UserDetailService(UserService userService) {
        this.userService = userService;
    }
    
    @Autowired
    private HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Assert.notNull(username,"aut username can not be null");

        String type = request.getParameter("type");
        // 普通用户认证
        UserTokenVo user = new UserTokenVo();
        if ("user".equals(type)){
            UmsUser umsUser = userServiceRPC.getUserByUniqueId(username).getData();

            if (umsUser == null)
                return null;

            user = UserTokenVo.builder().id(umsUser.getId()).password(umsUser.getPassword())
                       .username(umsUser.getUsername()).nickname(umsUser.getNickname())
                       .avatar(umsUser.getAvatar()).description(umsUser.getDescription())
                       .blog_address(umsUser.getBlogAddress())
                       .accountNonExpired(umsUser.getAccountNonExpired())
                       .accountNonLocked(umsUser.getAccountNonLocked()).credentialsNonExpired(umsUser.getCredentialsNonExpired())
                       .enabled(umsUser.getEnabled()).authorities(null).build();

        }else if ("admin".equals(type)){
            //后台用户认证
            user = userService.getUserByUserUniqueId(username);
            user.setAuthorities(conveterUserAuthorities(user.getRoles()));
        }

        log.info("load {} dedail..      -- authenticationServer-server", username);
        return user;
    }


    protected List<SimpleGrantedAuthority> conveterUserAuthorities(List<String> roles) {
        List<SimpleGrantedAuthority> list = roles.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
        return list;
    }
}
