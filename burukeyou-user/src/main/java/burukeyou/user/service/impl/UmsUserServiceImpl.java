package burukeyou.user.service.impl;

import burukeyou.common.core.utils.RegexValidationUtils;
import burukeyou.user.entity.pojo.UmsUsers;
import burukeyou.user.mapper.UmsUserMapper;
import burukeyou.user.service.UmsUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Service
public class UmsUserServiceImpl extends ServiceImpl<UmsUserMapper, UmsUsers> implements UmsUserService {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private HttpServletRequest request;

    public UmsUserServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Boolean saveOrupdate(UmsUsers umsUsers){
        Assert.notNull(umsUsers,"UmsUsers to create or update must not be null");

        String ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        if (StringUtils.isBlank(ip))
            ip = request.getHeader("REMOTE_ADDR");

        request.getRemoteHost();

        umsUsers = UmsUsers.builder().password(passwordEncoder.encode(umsUsers.getPassword()))
                .avatar("/xxx/xx/xx").createHost(ip).exp(0).fansCount(0).followCount(0)
                .accountNonExpired(true).accountNonLocked(true).credentialsNonExpired(true).enabled(true)
                .deleted(false).build();

        return this.save(umsUsers);
    }

    @Override
    public UmsUsers getByUniqueId(String uniqueId) {
        Assert.notNull(uniqueId,"query param uniqueId must not be null");

        // judge uniqueId is mobile or email or username or id
        if (RegexValidationUtils.validateeUsername(uniqueId)){
            return this.getOne(new QueryWrapper<UmsUsers>().eq("username",uniqueId));
        }else if (RegexValidationUtils.validateMobile(uniqueId)){
            return this.getOne(new QueryWrapper<UmsUsers>().eq("mobile",uniqueId));
        }else if (RegexValidationUtils.validateMobile(uniqueId)){
            return this.getOne(new QueryWrapper<UmsUsers>().eq("email",uniqueId));
        }else if (false){
            // todo query by id
            return null;
        }else
            return null;
    }
}
