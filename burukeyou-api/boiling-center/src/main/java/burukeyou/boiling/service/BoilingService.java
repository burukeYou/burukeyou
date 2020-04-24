package burukeyou.boiling.service;

import burukeyou.boiling.entity.dto.BoilingDto;
import burukeyou.boiling.entity.dto.QueryConditionDto;
import burukeyou.boiling.entity.pojo.AmsBoiling;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public interface BoilingService {

    /**
     *  删除沸点
     * @param id
     * @return
     */
     boolean deleteById(String id);

    /**
     *      发布沸点
     * @param boilingDto
     * @return
     */
    boolean publish(BoilingDto boilingDto);

    /**
     *      多条件查询沸点
     * @param dto
     * @return
     */
    Page<AmsBoiling> getPageCondition(QueryConditionDto dto);
}
