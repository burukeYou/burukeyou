package burukeyou.common.core.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

public class CustomBeanUtils {

    public static <T> T copyProperties(@Nullable Object source, Class<T> targetClass){
        Assert.notNull(targetClass, "Target class must not be null");
        if (source == null) return null;
        try {
            T targetInstance = targetClass.newInstance();
            BeanUtils.copyProperties(source,targetInstance,getNullPropertyNames(source));
            return targetInstance;
        } catch (Exception e) {
           throw new RuntimeException("Failed to new " + targetClass.getName() + " instance or copy properties", e);
        }
    }

    public static void copyProperties(@NonNull Object source,@NonNull Object target){
        Assert.notNull(source, "source object must not be null");
        Assert.notNull(target, "target object must not be null");

        try {
            BeanUtils.copyProperties(source,target,getNullPropertyNames(source));
        } catch (BeansException e) {
            throw new RuntimeException("Failed to copy properties", e);
        }
    }



    // Get a collection of attribute names with empty attribute values
    private static String[] getNullPropertyNames(@NonNull Object source) {
        Set<String> stringSet = getNullPropertyNameSet(source);
        String[] strings = stringSet.toArray(new String[0]);
        return strings;
    }
    @NonNull
    private static Set<String> getNullPropertyNameSet(@NonNull Object source){
        Assert.notNull(source,"source object must not be null");

        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = beanWrapper.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor e : pds) {
            if (beanWrapper.getPropertyValue(e.getName()) == null)
                emptyNames.add(e.getName());
        }
        return emptyNames;
    }

}
