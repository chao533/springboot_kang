package com.kang.common.aop;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.kang.common.anno.RequestLimit;
import com.kang.common.constant.RedisConstants;
import com.kang.common.exception.RequestLimitException;
import com.kang.common.utils.IPUtils;
import com.kang.mapper.redis.RedisMapper;

/**
　 * <p>Title: RequestLimitAspect</p> 
　 * <p>Description: IP限流（基于Redis）</p> 
　 * @author CK 
　 * @date 2020年4月16日
 */
@Aspect
@Component
public class LimitAspect_1 {
	
	private Logger logger = LoggerFactory.getLogger(LimitAspect_1.class);
	
	@Autowired
	private RedisMapper redisMapper;
	
	@Pointcut("@annotation(com.kang.common.anno.RequestLimit)")
    private void limitPointCut(){}

	@Before("limitPointCut()")
	public void requestLimit(final JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		RequestLimit limit = method.getAnnotation(RequestLimit.class);
		
		String ip = IPUtils.getIpAddr();
		String url = getRequest().getRequestURL().toString();
		String key = RedisConstants.KANG_REQUEST_LIMIT.concat(ip);
		Object obj = redisMapper.get(key);
		int count = obj == null ? 0 : Integer.parseInt(obj.toString());
		
		if (count >= limit.count()) {
			logger.info("用户IP[" + ip + "]访问地址[" + url + "]超过了限定的次数[" + limit.count() + "]");
			throw new RequestLimitException("访问的太频繁了");
		}
		
		if(count == 0) {
			redisMapper.set(key, 1,100l); // 设置100过期
		} else {
			redisMapper.set(key, count + 1,100l); // 设置100过期
		}
		
	}
	
	public HttpServletRequest getRequest() {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		return (requestAttributes == null) ? null : ((ServletRequestAttributes) requestAttributes).getRequest();
	}
}
