package burukeyou.notification.mapper;

import burukeyou.notification.entity.pojo.SysNotification;
import burukeyou.notification.entity.pojo.SysNotificationHistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NotificationHistoryMapper extends BaseMapper<SysNotificationHistory> {

}
