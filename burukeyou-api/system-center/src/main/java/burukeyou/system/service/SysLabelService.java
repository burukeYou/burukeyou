package burukeyou.system.service;

import burukeyou.system.entity.dto.QueryLabelConditionDto;
import burukeyou.system.entity.pojo.SysLabel;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SysLabelService extends IService<SysLabel> {

    /**
     *  delete by id
     * @param id
     * @return
     */
    boolean deleteLabel(String id);

    /**
     *  多条件分页查找标签
     * @param conditionDto
     * @return
     */
    Page<SysLabel> getLabelPage(QueryLabelConditionDto conditionDto);

    /**
     *  保存/修改标签
     * @param sysLabel
     * @return
     */
    boolean saveOrUpdateLabel(SysLabel sysLabel);

    /**
     *  更新关注数量
     * @param id
     * @param count
     * @return
     */
    int updateFoucusCount(String id, int count);
}
