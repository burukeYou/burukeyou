package burukeyou.system.service.impl;

import burukeyou.system.entity.dto.QueryTopicConditionDto;
import burukeyou.system.entity.pojo.SysTopic;
import burukeyou.system.mapper.SysTopicMapper;
import burukeyou.system.service.SysTopicService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
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
        //  todo 决出沸点和话题的关系
        return super.removeById(id);
    }

    @Override
    public Page<SysTopic> getTopicPage(QueryTopicConditionDto conditionDto) {
        Page<SysTopic> of = new Page<>(conditionDto.getPage(), conditionDto.getSize());

        if (StringUtils.isNotBlank(conditionDto.getOrderField())){
            if ("Asc".equals(conditionDto.getOrder()))
                of.addOrder(OrderItem.asc(conditionDto.getOrderField()));
            else if ("Desc".equals(conditionDto.getOrder()))
                of.addOrder(OrderItem.desc(conditionDto.getOrderField()));
        }
        return StringUtils.isNotBlank(conditionDto.getName()) ? super.page(of, new QueryWrapper<SysTopic>().lambda().like(SysTopic::getName,conditionDto.getName())) : super.page(of);
    }
}
