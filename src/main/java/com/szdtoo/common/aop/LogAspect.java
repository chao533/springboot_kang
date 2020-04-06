package com.szdtoo.common.aop;

import java.lang.reflect.Method;

import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.szdtoo.common.msg.ErrorCode;
import com.szdtoo.common.msg.Message;

/**
 * <p>Title: LogAspect</p>  
 * <p>Description:服务入口日志切面 </p>  
 * @author chaokang  
 * @date 2018年12月3日
 */
@Aspect
@Component("logAspect")
@Order(0)
public class LogAspect {
	
	private Logger log = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(* com.szdtoo.controller..*.*(..))")
    public void logPointCut() {
    }
    
    @Around(value = "logPointCut()")
    public Object logOperate(ProceedingJoinPoint joinPoint) {
    	
    	StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object result = null;
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        String methodName = method.getDeclaringClass().getSimpleName() + "." + method.getName();
        
        log.info("[log aop] begin;method:{}", methodName);
        try {
            result = joinPoint.proceed();
            log.info("[log aop] end;method:{},result:{},耗时:{}",
                    methodName, result, stopWatch.getTime());
        } catch (Throwable e) {
        	e.printStackTrace();
            log.error("[log aop] exception;method:{};耗时:{}",
                    methodName, stopWatch.getTime());
            result = new Message<String>(ErrorCode.ERROR,e.getMessage());
        }
        return result;
    }

}
