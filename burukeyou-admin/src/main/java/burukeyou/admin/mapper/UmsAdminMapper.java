package burukeyou.admin.mapper;

import burukeyou.admin.entity.dto.QueryUserConditionDto;
import burukeyou.admin.entity.pojo.UmsAdmin;
import burukeyou.admin.entity.vo.UmsAdminVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface UmsAdminMapper extends BaseMapper<UmsAdmin> {


    Page<UmsAdminVO> getListByCondition(@Param("page") Page<UmsAdminVO> page, @Param("dto") QueryUserConditionDto dto);

    UmsAdminVO getListByCondition( @Param("dto") QueryUserConditionDto dto);

}
