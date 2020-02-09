package burukeyou.common.core.utils;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.FieldError;

import javax.validation.*;
import java.util.*;


public class ValidationUtils {

    private static Validator validator;

    private ValidationUtils() {}


    // signal create
    public static Validator getValidator() {
        if (validator == null){
            synchronized(Validator.class){
                ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
                validator = factory.getValidator();
            }
        }
        return validator;
    }

    /**
     *
     * @param obj 待校验的对象
     * @param groups 待校验的组
     * @return 成功返回null,否则返回错误信息
     */
    public static Object validate(Object obj, Class<?>... groups) {
        Validator vd= getValidator();

        // 如果校验成功，该集合为空；否则，集合中的每一个元素（ConstraintViolation类型）对应一个违反的约束对象
        Set<ConstraintViolation<Object>> validate = vd.validate(obj, groups);

        //
        return !CollectionUtils.isEmpty(validate) ? mapWithValidError(validate) : null;
    }

    // 将字段验证错误转换为标准的map型，key:value = field:message
    @NonNull
    public static Map<String, String> mapWithValidError(Set<ConstraintViolation<Object>> cvs) {
        if (CollectionUtils.isEmpty(cvs)) {
            return Collections.emptyMap();
        }

        Map<String, String> errMap = new HashMap<>(4);
        cvs.forEach(e->errMap.put(e.getPropertyPath().toString(),e.getMessage()));
        return errMap;
    }



    //  将字段验证错误转换为标准的map型，key:value = field:message
    public static Map<String, String> mapWithFieldError(@Nullable List<FieldError> fieldErrors) {
        if (CollectionUtils.isEmpty(fieldErrors)) {
            return Collections.emptyMap();
        }

        Map<String, String> errMap = new HashMap<>(4);
        fieldErrors.forEach(fieldError->errMap.put(fieldError.getField(),fieldError.getDefaultMessage()));

        return errMap;
    }





}
