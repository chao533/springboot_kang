package com.kang.common.aop;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.kang.common.exception.ServiceException;
/**
　 * <p>Title: LockAspect</p> 
　 * <p>Description: 同步锁 AOP</p> 
　 * @author CK 
　 * @date 2020年4月15日
 */
@Aspect
@Component("lockAspect")
@Order(2) //order越小越是最先执行，但更重要的是最先执行的最后结束。order默认值是2147483647
public class LockAspect {
	/**
     * 思考：为什么不用synchronized
     * service 默认是单例的，并发下lock只有一个实例
     */
	private static Lock lock = new ReentrantLock(true);//互斥锁 参数默认false，不公平锁  
	
	@Pointcut("@annotation(com.kang.common.anno.LockAnnotation)") 
	public void lockPointCut() {}
	
    @Around("lockPointCut()")
    public  Object lockAround(ProceedingJoinPoint joinPoint) { 
    	lock.lock();
    	Object obj = null;
		try {
			obj = joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
			throw new ServiceException("lockAspect操作失败");    
		} finally{
			lock.unlock();
		}
    	return obj;
    } 
}
