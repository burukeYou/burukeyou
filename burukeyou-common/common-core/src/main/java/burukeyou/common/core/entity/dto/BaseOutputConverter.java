package burukeyou.common.core.entity.dto;

import burukeyou.common.core.utils.CustomBeanUtils;
import org.springframework.lang.NonNull;

/**
 *
 *  将Dto转换为Vo
 *
 */
public interface BaseOutputConverter<Vo extends BaseOutputConverter<Vo,Dto>,Dto> {

    @NonNull
    default <T extends Vo> T convertFrom(@NonNull Dto dto){
        CustomBeanUtils.copyProperties(dto,this);
        return (T)this;
    }

}
