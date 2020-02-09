package burukeyou.user.service.impl;

import burukeyou.auth.authClient.entity.CurrentUserInfo;
import burukeyou.user.entity.pojo.UmsColumn;
import burukeyou.user.mapper.UmsColumnMapper;
import burukeyou.user.service.UserColumnService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import static burukeyou.common.core.utils.SystemConstant.PendingReview;


@Service
public class UserColumnServiceImpl extends ServiceImpl<UmsColumnMapper, UmsColumn> implements UserColumnService {

    @Override
    public Boolean saveOrupdate(UmsColumn umsColumn) {
        Assert.notNull(umsColumn,"umsColumn to create or update cant not be null");

        if (StringUtils.isBlank(umsColumn.getImage()))
            umsColumn.setImage("/xxx/xx");

        CurrentUserInfo principal = null;
        if ( SecurityContextHolder.getContext().getAuthentication() != null){
             principal = (CurrentUserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }

        if (principal == null)
            return false;

        umsColumn.setIstop(false);
        umsColumn.setState(PendingReview);
        umsColumn.setUserId(principal.getId());

        return this.saveOrUpdate(umsColumn);
    }
}
