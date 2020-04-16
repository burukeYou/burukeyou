package burukeyou.focus.service.impl;

import burukeyou.auth.authClient.util.AuthUtils;
import burukeyou.focus.entity.enums.FocusTargetEnums;
import burukeyou.focus.entity.pojo.UmsFocus;
import burukeyou.focus.mapper.UmsFocusMapper;
import burukeyou.focus.service.RedisFocusService;
import burukeyou.focus.service.UmsFocusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UmsFocusServiceImpl extends ServiceImpl<UmsFocusMapper, UmsFocus> implements UmsFocusService {


    @Autowired
    private RedisFocusService redisFocusService;


    // todo 保证一致性
    @Override
    public boolean focus(String targetId, String targetType) {
        redisFocusService.focus(AuthUtils.ID(),targetId,targetType);
        return true;
    }

    @Override
    public boolean cancelFocus(String targetId,String targetType) {
        redisFocusService.cancelFocus(AuthUtils.ID(),targetId,targetType);
        return true;
    }


    // [秒] [分] [小时] [日] [月] [周] [年]
   /* @Scheduled(cron = "30 * * * * ?" )
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void synFocusDataToDB(){
        log.info("执行关注数据同步");
        redisFocusService.focusStatusDataSyncToDB();
        redisFocusService.focusCountDataSyncToDB();
    }*/


}
