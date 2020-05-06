package burukeyou.notification.service.impl;

import burukeyou.auth.authClient.util.AuthUtils;
import burukeyou.common.rabbitmq.entity.bo.NotificationContent;
import burukeyou.notification.entity.enums.NotificationStatusEnum;
import burukeyou.notification.entity.enums.NotificationTypeEnum;
import burukeyou.notification.entity.pojo.SysNotification;
import burukeyou.notification.entity.pojo.SysNotificationHistory;
import burukeyou.notification.entity.vo.NotificationVo;
import burukeyou.notification.mapper.NotificationMapper;
import burukeyou.notification.service.NotificationHistoryService;
import burukeyou.notification.service.NotificationService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl extends ServiceImpl<NotificationMapper, SysNotification> implements NotificationService {

    private final NotificationHistoryService notificationHistoryService;

    public NotificationServiceImpl(NotificationHistoryService notificationHistoryService) {
        this.notificationHistoryService = notificationHistoryService;
    }

    @Override
    public boolean publishNotification(String acceptId, String content,String type) {
        SysNotification notification = SysNotification.builder().status(NotificationStatusEnum.UNREAD.VALUE())
                .type(type).acceptId(acceptId).content(content).build();
        return super.save(notification);
    }

    @Override
    public Page<NotificationVo> getNotificationPage(String acceptId, String type,int page,int size) {
        if (!acceptId.equals(AuthUtils.ID()))
            return null;

        Page<SysNotification> notificationPage = super.page(new Page<>(page,size), new QueryWrapper<SysNotification>()
                                                .lambda().eq(SysNotification::getAcceptId, acceptId)
                                                .eq(SysNotification::getType, type));

        List<NotificationVo> voList = notificationPage.getRecords().stream().map(e -> {
            NotificationVo vo = new NotificationVo();
            BeanUtils.copyProperties(e, vo);
            vo.setContent(JSONObject.parseObject(e.getContent(), NotificationContent.class));
            return vo;
        }).collect(Collectors.toList());

        Page<NotificationVo> voPage = new Page<>();
        voPage.setRecords(voList);
        voPage.setTotal(notificationPage.getTotal());
        voPage.setSize(notificationPage.getSize());
        voPage.setCurrent(notificationPage.getCurrent());
        voPage.setPages(notificationPage.getPages());
        return voPage;
    }

    @Override
    public boolean readNotification(String id) {
        SysNotification notification = super.getById(id);
        if (notification == null || !notification.getAcceptId().equals(AuthUtils.ID()))
            return false;

        if(notification.getType().equals(NotificationTypeEnum.USER.VALUE())){
            SysNotification sysNotification = SysNotification.builder().status(NotificationStatusEnum.READ.VALUE()).id(id).build();
            super.updateById(sysNotification);
        }else {
            notificationHistoryService.save(SysNotificationHistory.builder().createTime(LocalDateTime.now()).notificationId(id).userId(notification.getAcceptId()).build());
        }
        return true;
    }

}
