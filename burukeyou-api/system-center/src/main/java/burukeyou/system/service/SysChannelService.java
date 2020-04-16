package burukeyou.system.service;


import burukeyou.system.entity.pojo.SysChannel;
import burukeyou.system.entity.vo.ChannelVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SysChannelService extends IService<SysChannel> {
    /**
     *  删除
     * @param id
     * @return
     */
    boolean deleteChannel(String id);

    /**
     *  条件查询
     * @param name
     * @param page
     * @param size
     * @return
     */
    Page<ChannelVo> getChannelPage(String name, int page, int size);
}
