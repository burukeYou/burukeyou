package burukeyou.boiling.service;

import burukeyou.boiling.entity.dto.BoilingDto;
import burukeyou.boiling.entity.pojo.AmsBoiling;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public interface BoilingService {

     boolean deleteById(String id);

    boolean publish(BoilingDto boilingDto);

    Page<AmsBoiling> getListByUserId(String userId, int page, int size);
}
