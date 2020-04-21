package burukeyou.system.service.impl;

import burukeyou.auth.authClient.util.AuthUtils;
import burukeyou.common.core.entity.vo.ResultVo;
import burukeyou.system.entity.dto.QueryLabelConditionDto;
import burukeyou.system.entity.enums.FocusStatusEnum;
import burukeyou.system.entity.enums.FocusTargetEnums;
import burukeyou.system.entity.pojo.SysLabel;
import burukeyou.system.mapper.SysLabelMapper;
import burukeyou.system.rpc.FocusServiceRPC;
import burukeyou.system.service.SysLabelService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


@Service
public class SysLableServiceImpl extends ServiceImpl<SysLabelMapper, SysLabel> implements SysLabelService {

    @Autowired
    private final FocusServiceRPC focusServiceRPC;

    public SysLableServiceImpl(FocusServiceRPC focusServiceRPC) {
        this.focusServiceRPC = focusServiceRPC;
    }

    @Override
    public boolean deleteLabel(String id) {
        //todo remove relation with article
        return super.removeById(id);
    }

    @Override
    public Page<SysLabel> getLabelPage(QueryLabelConditionDto conditionDto) {
        Page<SysLabel> of = new Page<>();
        if (FocusStatusEnum.ALL.VALUE().equals(conditionDto.getFocusStatus()) || StringUtils.isBlank(conditionDto.getFocusStatus())){
            if (StringUtils.isNotBlank(conditionDto.getOrderField())){
                of.addOrder("Asc".equals(conditionDto.getOrder()) ?OrderItem.asc(conditionDto.getOrderField()):OrderItem.desc(conditionDto.getOrderField()));
            }
            of.setCurrent(conditionDto.getPage());
            of.setSize(conditionDto.getSize());
            return StringUtils.isNotBlank(conditionDto.getName()) ? super.page(of, new QueryWrapper<SysLabel>().lambda().like(SysLabel::getName,conditionDto.getName())) : super.page(of);
        }
        else if (StringUtils.isNotBlank(AuthUtils.ID())){
            ResultVo<Page<String>> res = focusServiceRPC.getUserFocusTargetPage(AuthUtils.ID(), FocusTargetEnums.LABEL.VALUE(), conditionDto.getPage(), conditionDto.getSize());
            if (res.getData() != null){
                Page<String> data = res.getData();
                if (!CollectionUtils.isEmpty(data.getRecords()))
                    of.setRecords(this.listByIds(data.getRecords()));
                of.setCurrent(data.getPages());
                of.setSize(data.getSize());
                of.setTotal(data.getTotal());
            }
            return of;
        }
        return of;
    }

    @Override
    public boolean saveOrUpdateLabel(SysLabel sysLabel) {
        if (StringUtils.isBlank(sysLabel.getId())){
            sysLabel.setFocusCount(0);
            sysLabel.setArticleCount(0);
        }
        return super.saveOrUpdate(sysLabel);
    }


}
