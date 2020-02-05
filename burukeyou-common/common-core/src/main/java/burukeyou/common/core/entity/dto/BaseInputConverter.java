package burukeyou.common.core.entity.dto;

import burukeyou.common.core.utils.CustomBeanUtils;
import burukeyou.common.core.utils.ReflectionUtils;
import org.springframework.lang.Nullable;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * 将当前对象复制属性转换为对象T
 * @param <T> 转换后的对象
 */
public interface BaseInputConverter<T> {

    default T converTo(){
        ParameterizedType currentparamterizedType = getCurrentparamterizedType();
        Objects.requireNonNull(currentparamterizedType,"Cannot fetch actual type because parameterized type is null");
        Type actualTypeArgument = currentparamterizedType.getActualTypeArguments()[0];
        Class<T> tClazz = (Class<T>) actualTypeArgument;
        return CustomBeanUtils.copyProperties(this, tClazz);
    }

    default void converTo(T dto){
         CustomBeanUtils.copyProperties(this, dto);
    }

    @Nullable
    default ParameterizedType getCurrentparamterizedType(){
        return ReflectionUtils.getParameterizedType(BaseInputConverter.class,this.getClass());
    }

}
