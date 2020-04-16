package burukeyou.system.service;

import burukeyou.system.entity.pojo.SysTopic;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public interface SysTopicService {
    /**
     *  发布话题
     * @param converTo
     * @return
     */
    boolean publicTopic(SysTopic converTo);

    /**
     *  删除话题
     * @param id
     * @return
     */
    boolean deleteTopic(String id);

    Page<SysTopic> getTopicPage(String name, int page, int size);
}
