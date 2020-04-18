//package com.kang.common.aop;
//
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.TimeUnit;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import com.google.common.cache.CacheBuilder;
//import com.google.common.cache.CacheLoader;
//import com.google.common.cache.LoadingCache;
//import com.google.common.util.concurrent.RateLimiter;
//import com.kang.common.exception.RequestLimitException;
//import com.kang.common.utils.IPUtils;
//
///**
//　 * <p>Title: RequestLimitAspect</p> 
//　 * <p>Description: IP限流（令牌桶）</p> 
//　 * @author CK 
//　 * @date 2020年4月16日
// */
//@Aspect
//@Component
//public class LimitAspect_2 {
//	
//	private Logger logger = LoggerFactory.getLogger(LimitAspect_2.class);
//	
//	//每秒只发出100个令牌，此处是单进程服务的限流，内部采用令牌捅算法实现
//    private static  RateLimiter rateLimiter = RateLimiter.create(5.0);
//	
//	
//	@Pointcut("@annotation(com.kang.common.anno.RequestLimit)")
//    private void limitPointCut(){}
//
//	@Before("limitPointCut()")
//	public void requestLimit(final JoinPoint joinPoint) {
////		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
////		Method method = signature.getMethod();
////		RequestLimit limit = method.getAnnotation(RequestLimit.class);
//		
//		String ip = IPUtils.getIpAddr();
//		logger.info("访问IP地址为：{}",ip);
//		Boolean flag = rateLimiter.tryAcquire();
//		if(!flag){
//			throw new RequestLimitException("访问的太频繁了");
//		}
//		
//	}
//	
//	public HttpServletRequest getRequest() {
//		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//		return (requestAttributes == null) ? null : ((ServletRequestAttributes) requestAttributes).getRequest();
//	}
//}
