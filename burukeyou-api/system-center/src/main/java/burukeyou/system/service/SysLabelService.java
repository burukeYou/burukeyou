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

    Page<SysLabel> getLabelPage(QueryLabelConditionDto conditionDto);

    boolean saveOrUpdateLabel(SysLabel sysLabel);
}
