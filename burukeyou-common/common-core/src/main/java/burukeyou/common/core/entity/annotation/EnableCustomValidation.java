package burukeyou.common.core.entity.annotation;


import burukeyou.common.core.aspect.ParamValidAOP;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({ParamValidAOP.class})
public @interface EnableCustomValidation {
}
