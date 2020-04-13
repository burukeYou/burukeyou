package burukeyou.focus.service.impl;

import burukeyou.auth.authClient.util.AuthUtils;
import burukeyou.focus.entity.enums.FocusTargetEnums;
import burukeyou.focus.entity.pojo.UmsFocus;
import burukeyou.focus.mapper.UmsFocusMapper;
import burukeyou.focus.service.UmsFocusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class UmsFocusServiceImpl extends ServiceImpl<UmsFocusMapper, UmsFocus> implements UmsFocusService {

    // todo 保证一致性
    @Override
    public void focus(String targetId, String targetType) {
        UmsFocus build = UmsFocus.builder().targetId(targetId).targetType(targetType).userId(AuthUtils.ID()).userAvatar(AuthUtils.AVATAR()).userNickname(AuthUtils.USERNAME()).build();


        if (FocusTargetEnums.USER.VALUE().equals(targetType)){

        }else if (FocusTargetEnums.TOPIC.VALUE().equals(targetType)){

        }else if (FocusTargetEnums.LABEL.VALUE().equals(targetType)){

        }


        super.save(build);
    }
}
