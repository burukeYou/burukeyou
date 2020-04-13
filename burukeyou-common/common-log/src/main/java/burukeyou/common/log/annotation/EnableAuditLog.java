package burukeyou.common.log.annotation;

import burukeyou.common.log.aspect.AuditLogAOP;
import burukeyou.common.log.properties.AuditLogProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({AuditLogAOP.class})
public @interface EnableAuditLog {

}
