package burukeyou.common.core.utils;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class BaseConverterUtils {
    private BaseConverterUtils() {}

    // InputConverter.class    LoginDto.class
    @Nullable
    public static ParameterizedType getParameterizedType(@NonNull Class<?> interfaceType,Class<?> implementationClass){
        Assert.notNull(interfaceType, "Interface type must not be null");
        Assert.isTrue(interfaceType.isInterface(), "The give type must be an interface");

        if (implementationClass == null) return null;

        ParameterizedType pt = getParameterizedType(interfaceType, implementationClass.getGenericInterfaces());
        if (pt != null) return pt;

        Class<?> superclass = implementationClass.getSuperclass();
        return getParameterizedType(interfaceType,superclass); // 递归向父类继续查找
    }


    // 从参数化类型genericTypes中找到和superType字节码对象相同的数据类型的ParameterizedType
    @Nullable
    public static ParameterizedType getParameterizedType(@NonNull Class<?> superType, Type...genericTypes){
        Assert.notNull(superType, "Interface or super type must not be null");

        ParameterizedType currentParameterizedType = null;
        for (Type e : genericTypes) {
            if (e instanceof ParameterizedType){
                ParameterizedType pt = (ParameterizedType)e;
                if (pt.getRawType().getTypeName().equals(superType.getTypeName())){
                    currentParameterizedType = pt;
                    break;
                }
            }
        }
        return currentParameterizedType;
    }

}
