package burukeyou.boiling.mapper;

import burukeyou.boiling.entity.dto.QueryConditionDto;
import burukeyou.boiling.entity.pojo.AmsBoiling;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BoillingMapper extends BaseMapper<AmsBoiling> {

    Page<AmsBoiling> getPageCondition(@Param("page")Page<AmsBoiling> page, @Param("dto")QueryConditionDto dto);
}
