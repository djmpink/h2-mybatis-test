package cn.teamstack.common.core.aspect;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by zhouli on 2017/1/10.
 */
@Aspect
@Component
public class LoggerAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggerAspect.class);

    @Before("execution(* cn.teamstack.controller.*.*.*(..))")
    public void requestLog(JoinPoint point) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();
        ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
        if (apiOperation != null) {
            logger.info("[API] | {}", apiOperation.value());
        }

        logger.info("[请求] | {}", JSON.toJSONString(point.getArgs()));
    }

    @AfterReturning(pointcut = "execution(* cn.teamstack.controller.*.*.*(..))", returning = "returnValue")
    public void responseLog(JoinPoint point, Object returnValue) throws Throwable {
        logger.info("[返回] | {}", JSON.toJSONString(returnValue));
    }

//    @Pointcut("execution(* cn.teamstack.controller.*.*.*(..))")
//    public void logPointcut() {
//    }
//
//    @Around("logPointcut()")
//    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
//        Object response = pjp.proceed();
//        pjp.getArgs();
//        logger.info("[请求]|{}", JSON.toJSONString(pjp.getArgs()));
//        logger.info("[返回]|{}", JSON.toJSONString(response));
//        return response;
//
//    }
}
