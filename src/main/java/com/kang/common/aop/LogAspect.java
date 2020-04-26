package com.kang.common.aop;

import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.kang.common.exception.RequestLimitException;
import com.kang.common.exception.ServiceException;
import com.kang.common.exception.TokenValidationException;

import cn.hutool.json.JSONUtil;

/**
 * <p>Title: LogAspect</p>  
 * <p>Description:服务入口日志切面 </p>  
 * @author chaokang  
 * @date 2018年12月3日
 */
@Aspect
@Component("logAspect")
@Order(1)
public class LogAspect {
	
	private Logger log = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(* com.kang.controller..*.*(..))")
    public void logPointCut() {}
    
    @Around(value = "logPointCut()")
    public Object logOperate(ProceedingJoinPoint joinPoint) {
    	StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = null;
        try {
            result = joinPoint.proceed();
        }catch (RequestLimitException e) {
            throw new RequestLimitException(e.getMessage());
        }catch (ServiceException e) {
        	e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }catch (TokenValidationException e) {
        	e.printStackTrace();
            throw new TokenValidationException(e.getMessage());
        } catch (Throwable e) {
        	e.printStackTrace();
            throw new ServiceException("logAspect操作失败");
        } finally {
        	printLog(joinPoint, result, stopWatch.getTime());
        }
        return result;
    }
    
    
    private void printLog(ProceedingJoinPoint joinPoint,Object result,Long spendTime) {
    	String format = "线程名称: {}, 目标类名: {}, 目标方法: {}, 调用参数: {}, 返回结果: {}, 花费时间: {}";
    	String threadName = Thread.currentThread().getName();
    	Object className = joinPoint.getTarget().getClass().getName();
    	String methodName = joinPoint.getSignature().getName();
    	String args = JSONUtil.toJsonStr(joinPoint.getArgs());
    	log.info(format,threadName,className,methodName,args,result,spendTime);
    }
    
    
}
