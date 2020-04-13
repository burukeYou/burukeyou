package burukeyou.common.log.aspect;


import burukeyou.common.log.annotation.AuditLog;
import burukeyou.common.log.entity.AuditingLog;
import burukeyou.common.log.properties.AuditLogProperties;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 *      @annotation: 方法级别
 *      @within: 对象级别
 */

@Slf4j
@Aspect
@Component
@ComponentScan("burukeyou.common.log")
public class AuditLogAOP {

    @Value("${spring.application.name}")
    private String applicationName;

    private  AuditLogProperties auditLogProperties = new AuditLogProperties();

    //private AuditLogService auditLogService;

    // 解析spel表达式
    private SpelExpressionParser spelExpressionParser = new SpelExpressionParser();

    // 获得某一方法的所有参数名
    private DefaultParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

    public AuditLogAOP(AuditLogProperties auditLogProperties) {
        this.auditLogProperties = auditLogProperties;
        //this.auditLogService = auditLogService;
    }

    @Before(value = "@annotation(enableAuditLog) || @within(enableAuditLog)")
    public void getAutiLogInfo(JoinPoint joinPoint, AuditLog enableAuditLog){
        if (!auditLogProperties.getEnabled())
            return;

      /*  if (auditLogService == null) {
            log.warn("AuditLogAOP - auditService cant not be null");
            return;
        }
*/
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        if (enableAuditLog == null) {
            enableAuditLog = signature.getMethod().getAnnotation(AuditLog.class);
            //enableAuditLog = joinPoint.getTarget().getClass().getDeclaredAnnotation(EnableAuditLog.class);
        }

        AuditingLog auditlog = AuditingLog.builder().applicationName(applicationName).createTime(LocalDateTime.now()).build();

        auditlog.setInterfaceName(signature.getDeclaringTypeName()+"."+signature.getName());

        String logInfo = enableAuditLog.logInfo();

        // 方法参数名数组
        String[] parameterNames = parameterNameDiscoverer.getParameterNames(signature.getMethod());
        if (parameterNames != null && parameterNames.length > 0){
            EvaluationContext context = new StandardEvaluationContext();

            //获取方法参数值
            Object[] args = joinPoint.getArgs();
            for (int i = 0; i < args.length; i++) {
                context.setVariable(parameterNames[i],args[i]); // 替换spel里的变量值为实际值， 比如 #user -->  user对象
            }

            // 解析
            String opeationInfo = spelExpressionParser.parseExpression(logInfo).getValue(context).toString();
            auditlog.setOperationInfo(opeationInfo);
        }

        //
        log.info(auditlog.toString());

        //todo 实现autdit-service异步化存储日志   1. ->文件->logstash-> es/db  /  或者   -> mq -> es/db

    }
}
