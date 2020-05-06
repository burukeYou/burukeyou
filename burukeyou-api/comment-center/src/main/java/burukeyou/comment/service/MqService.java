package burukeyou.comment.service;

import burukeyou.common.rabbitmq.entity.bo.NotificationDto;

public interface MqService {

    /**
     *      发布通知
     * @param notificationDto
     */
    void publishNotification(NotificationDto notificationDto);
}
