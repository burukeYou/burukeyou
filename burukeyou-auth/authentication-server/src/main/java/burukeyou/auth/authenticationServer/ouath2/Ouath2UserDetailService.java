package burukeyou.auth.authenticationServer.ouath2;

import burukeyou.auth.authenticationServer.entity.vo.UserTokenVo;
import burukeyou.auth.authenticationServer.dao.UmsUserMapper;
import burukeyou.auth.authenticationServer.entity.pojo.UmsUser;
import burukeyou.auth.authenticationServer.service.local.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class Ouath2UserDetailService extends ServiceImpl<UmsUserMapper, UmsUser> implements UserDetailsService {

    private final UserService userService;

    public Ouath2UserDetailService(UserService userService) {
        this.userService = userService;
    }
    
    @Autowired
    private HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String type = request.getParameter("type");
        // 普通用户认证
        UserTokenVo user = new UserTokenVo();
        if ("user".equals(type)){
            UmsUser umsUser = this.getOne(new QueryWrapper<UmsUser>()
                    .eq("username", username)
                    .or().eq("mobile", username).or().eq("email", username));

            user = new UserTokenVo();
            user.setId(umsUser.getId());
            user.setPassword(umsUser.getPassword());
            user.setUsername(umsUser.getUsername());
            user.setNickname(umsUser.getNickname());
            user.setAvatar(umsUser.getAvatar());
            user.setAccountNonExpired(umsUser.getAccountNonExpired());
            user.setAccountNonLocked(umsUser.getAccountNonLocked());
            user.setCredentialsNonExpired(umsUser.getCredentialsNonExpired());
            user.setEnabled(umsUser.getEnabled());
            user.setAuthorities(null);
        }else if ("admin".equals(type)){
            //后台用户认证
            user = userService.getUserByUserUniqueId(username);
            user.setAuthorities(conveterUserAuthorities(user.getRoles()));
        }

        log.info("load {} dedail..      -- authenticationServer-server", username);
        return user;
    }


    protected List<SimpleGrantedAuthority> conveterUserAuthorities(List<String> roles) {
        List<SimpleGrantedAuthority> list = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
        return list;
    }
}
