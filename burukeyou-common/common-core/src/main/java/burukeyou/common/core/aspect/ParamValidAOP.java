package burukeyou.common.core.aspect;

import burukeyou.common.core.entity.vo.ResultVo;
import burukeyou.common.core.utils.ValidationGroupRules.INSERT;
import burukeyou.common.core.utils.ValidationGroupRules.UPDATE;
import burukeyou.common.core.utils.ValidationUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Map;

@Aspect
@Component
public class ParamValidAOP {


    @Around("@annotation(burukeyou.common.core.entity.annotation.EnableParamValid)")
    public Object selectParamValidByIdValue(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Object args = joinPoint.getArgs()[0];
            Class<?> aClass = args.getClass();

            Field id = aClass.getDeclaredField("id");
            id.setAccessible(true);
            Object idValue = id.get(args);

            Object result = null;
            if (idValue == null) {
                result = ValidationUtils.validate(args, INSERT.class);
            } else {
                result = ValidationUtils.validate(args, UPDATE.class);
            }

            return result == null ? joinPoint.proceed() : ResultVo.error(JSONObject.toJSONString(result));
        }catch (Exception exception){
            return ResultVo.error("异常");
        }
    }






}
