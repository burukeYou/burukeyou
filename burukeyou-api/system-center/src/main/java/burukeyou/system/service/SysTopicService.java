package burukeyou.system.service;

import burukeyou.system.entity.dto.QueryTopicConditionDto;
import burukeyou.system.entity.pojo.SysTopic;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SysTopicService extends IService<SysTopic> {
    /**
     *  发布话题
     * @param converTo
     * @return
     */
    boolean saveOrUpdateTopic(SysTopic converTo);

    /**
     *  删除话题
     * @param id
     * @return
     */
    boolean deleteTopic(String id);

    Page<SysTopic> getTopicPage(QueryTopicConditionDto conditionDto);
}
