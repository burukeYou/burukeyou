package burukeyou.notification.service;

import burukeyou.notification.entity.vo.NotificationVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public interface NotificationService {

    /**     发布通知
     *
     * @param acceptId  接收者id： #000表所有用户，否则代表具体接收者id
     * @param content   通知内容
     * @param type  通知类型： 系统通知/用户通知
     * @return
     */
    boolean publishNotification(String acceptId, String content,String type);

    /**
     *    分页获取用户通知
     * @param acceptId
     * @param type
     * @param page
     * @param size
     * @return
     */
    Page<NotificationVo> getNotificationPage(String acceptId, String type, int page, int size);

    /**
     *      阅读通知s
     * @param id
     */
    boolean readNotification(String id);
}
