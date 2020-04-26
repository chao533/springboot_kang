package com.kang.common.aop;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
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
	
	private Logger logger = LoggerFactory.getLogger(LogAspect.class);
	
    @Pointcut("execution(* com.kang.controller..*.*(..))")
    public void logPointCut() {}
    
    @Around(value = "logPointCut()")
    public Object logOperate(ProceedingJoinPoint joinPoint) {
    	StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = null;
        boolean isSucc = true;
        try {
            result = joinPoint.proceed();
        }catch (RequestLimitException e) { // 处理限流异常
        	isSucc = false;
            throw new RequestLimitException(e.getMessage());
        }catch (ServiceException e) { // 处理业务异常
        	isSucc = false;
        	e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }catch (TokenValidationException e) { // 处理Token异常
        	isSucc = false;
        	e.printStackTrace();
            throw new TokenValidationException(e.getMessage());
        }catch (IllegalArgumentException e) { // 处理参数异常
        	isSucc = false;
        	e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }catch (AuthenticationException e) { // 处理登录认证异常
        	isSucc = false;
        	e.printStackTrace();
            throw new AuthenticationException(e.getMessage());
        }catch (AuthorizationException e) { // 处理用户授权异常
        	isSucc = false;
        	e.printStackTrace();
            throw new AuthorizationException(e.getMessage());
        } catch (Throwable e) {
        	isSucc = false;
        	e.printStackTrace();
            throw new ServiceException("logAspect操作失败");
        } finally {
        	printLog(joinPoint, result, stopWatch.getTime(),isSucc);
        }
        return result;
    }
    
    
    private void printLog(ProceedingJoinPoint joinPoint,Object result,Long spendTime,boolean isSucc) {
    	String format = "线程名称: {}, 目标类名: {}, 目标方法: {}, 调用参数: {}, 返回结果: {}, 花费时间: {}";
    	String threadName = Thread.currentThread().getName();
    	Object className = joinPoint.getTarget().getClass().getName();
    	String methodName = joinPoint.getSignature().getName();
    	String args = JSONUtil.toJsonStr(joinPoint.getArgs());
    	if(isSucc) {
    		logger.info(format,threadName,className,methodName,args,result,spendTime);
    	} else {
    		logger.error(format,threadName,className,methodName,args,result,spendTime);
    	}
    }
    
    
}
