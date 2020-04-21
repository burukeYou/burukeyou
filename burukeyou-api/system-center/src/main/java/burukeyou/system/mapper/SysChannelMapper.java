package burukeyou.system.mapper;

import burukeyou.system.entity.pojo.SysChannel;
import burukeyou.system.entity.vo.ChannelVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SysChannelMapper extends BaseMapper<SysChannel> {

    Page<ChannelVo> getPageByConfition(Page<ChannelVo> pg, String name);
}
