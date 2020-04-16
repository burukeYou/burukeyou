package burukeyou.system.service.impl;

import burukeyou.system.entity.pojo.SysChannel;
import burukeyou.system.entity.vo.ChannelVo;
import burukeyou.system.mapper.SysChannelMapper;
import burukeyou.system.service.SysChannelService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SysChannelServiceImpl extends ServiceImpl<SysChannelMapper, SysChannel> implements SysChannelService {


    @Override
    public boolean deleteChannel(String id) {
        // todo 删除文章和频道的关系

        return super.removeById(id);
    }

    @Override
    public Page<ChannelVo> getChannelPage(String name, int page, int size) {
        Page<SysChannel> pg = new Page<>(page,size);
        return baseMapper.getPageByConfition(pg,name);
    }
}
