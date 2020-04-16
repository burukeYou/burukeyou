package burukeyou.system.service.impl;

import burukeyou.system.entity.pojo.SysTopic;
import burukeyou.system.mapper.SysTopicMapper;
import burukeyou.system.service.SysTopicService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SysTopicServiceImpl extends ServiceImpl<SysTopicMapper, SysTopic> implements SysTopicService {
    @Override
    public boolean publicTopic(SysTopic converTo) {
        return false;
    }

    @Override
    public boolean deleteTopic(String id) {
        return false;
    }

    @Override
    public Page<SysTopic> getTopicPage(String name, int page, int size) {
        return null;
    }
}
