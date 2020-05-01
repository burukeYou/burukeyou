package burukeyou.user.service.impl;

import burukeyou.auth.authClient.util.AuthUtils;
import burukeyou.common.core.utils.RegexValidationUtils;
import burukeyou.user.entity.pojo.UmsUser;
import burukeyou.user.mapper.UmsUserMapper;
import burukeyou.user.service.UmsUserService;
import burukeyou.user.utils.CommonUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Service
public class UmsUserServiceImpl extends ServiceImpl<UmsUserMapper, UmsUser> implements UmsUserService {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private HttpServletRequest request;

    public UmsUserServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Boolean saveOrupdate(UmsUser umsUser){
        Assert.notNull(umsUser,"UmsUsers to create or update must not be null");

        if (umsUser.getId() == null){
            // save
            umsUser.setCreateHost(CommonUtils.getRemoteIpAddress(request));
            umsUser.setPassword(passwordEncoder.encode(umsUser.getPassword()));
            umsUser.setAvatar("/xxx/xx/xx");
            umsUser.setExp(0);
            umsUser.setFansCount(0);
            umsUser.setFollowCount(0);
            umsUser.setAccountNonExpired(true);
            umsUser.setAccountNonLocked(true);
            umsUser.setCredentialsNonExpired(true);
            umsUser.setEnabled(true);
            umsUser.setDeleted(false);
        }else if (!umsUser.getId().equals(AuthUtils.ID()))
            //update
            return false;

        return super.saveOrUpdate(umsUser);
    }

    @Override
    public UmsUser getByUniqueId(String uniqueId) {
        Assert.notNull(uniqueId,"query param uniqueId must not be null");
        // judge uniqueId is mobile or email or username or id
        if (RegexValidationUtils.validateeUsername(uniqueId)){
            return this.getOne(new QueryWrapper<UmsUser>().eq("username",uniqueId));
        }else if (RegexValidationUtils.validateMobile(uniqueId)){
            return this.getOne(new QueryWrapper<UmsUser>().eq("mobile",uniqueId));
        }else if (RegexValidationUtils.validateEmail(uniqueId)){
            return this.getOne(new QueryWrapper<UmsUser>().eq("email",uniqueId));
        }else {
            return  super.getById(uniqueId);
        }
    }
}
