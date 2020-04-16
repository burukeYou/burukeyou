package burukeyou.system.service.impl;

import burukeyou.system.entity.pojo.SysTopic;
import burukeyou.system.mapper.SysTopicMapper;
import burukeyou.system.service.SysTopicService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class SysTopicServiceImpl extends ServiceImpl<SysTopicMapper, SysTopic> implements SysTopicService {
    @Override
    public boolean saveOrUpdateTopic(SysTopic topic) {
        if (topic.getId() == null){
            topic.setBoilingCount(0);
            topic.setFocusCount(0);
        }
        return super.saveOrUpdate(topic);
    }

    @Override
    public boolean deleteTopic(String id) {
        return super.removeById(id);
    }

    @Override
    public Page<SysTopic> getTopicPage(String name, int page, int size) {
        Page<SysTopic> of = new Page<>(page, size);
        return StringUtils.isNotBlank(name) ? super.page(of, new QueryWrapper<SysTopic>()
                .lambda().like(SysTopic::getName,name)) : super.page(of);
    }
}
